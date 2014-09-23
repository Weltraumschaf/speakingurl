package de.weltraumschaf.speakingurl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link SlugImplementation}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementationTest {

    private final SlugImplementation sut = new SlugImplementation();

    @Test(expected = NullPointerException.class)
    public void currentCharacter_nullInput() {
        sut.currentCharacter(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void currentCharacter_negativeIndex() {
        sut.currentCharacter("", -1);
    }

    @Test
    public void currentCharacter() {
        final String fixture = "foo bar";

        assertThat(sut.currentCharacter(fixture, 0), is(equalTo("f")));
        assertThat(sut.currentCharacter(fixture, 1), is(equalTo("o")));
        assertThat(sut.currentCharacter(fixture, 2), is(equalTo("o")));
        assertThat(sut.currentCharacter(fixture, 3), is(equalTo(" ")));
        assertThat(sut.currentCharacter(fixture, 4), is(equalTo("b")));
        assertThat(sut.currentCharacter(fixture, 5), is(equalTo("a")));
        assertThat(sut.currentCharacter(fixture, 6), is(equalTo("r")));
    }

    @Test(expected = NullPointerException.class)
    public void generateAllowedCharatcers_nullSeparator() {
        sut.generateAllowedCharatcers(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateAllowedCharatcers_emptySeparator() {
        sut.generateAllowedCharatcers("");
    }

    @Test
    public void generateAllowedCharatcers() {
        assertThat(sut.generateAllowedCharatcers("-"), is(equalTo("\\Q-\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withUric() {
        final Options options = new Options();
        options.uric(true);
        final SlugImplementation sutWithUric = new SlugImplementation(options);

        assertThat(sutWithUric.generateAllowedCharatcers("-"), is(equalTo("\\Q-;?:@&=+$,/\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withUricWithoutSlash() {
        final Options options = new Options();
        options.uricWithoutSlash(true);
        final SlugImplementation sutWithUricWithoutSlash = new SlugImplementation(options);

        assertThat(sutWithUricWithoutSlash.generateAllowedCharatcers("-"), is(equalTo("\\Q-;?:@&=+$,\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withMark() {
        final Options options = new Options();
        options.mark(true);
        final SlugImplementation sutWithMark = new SlugImplementation(options);

        assertThat(sutWithMark.generateAllowedCharatcers("-"), is(equalTo("\\Q-.!~*'()\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withUricUricwithoutSlashAndMark() {
        final Options options = new Options();
        options.uric(true);
        options.uricWithoutSlash(true);
        options.mark(true);
        final SlugImplementation sutWithUricURicWithoutSlashAndMark = new SlugImplementation(options);

        assertThat(sutWithUricURicWithoutSlashAndMark.generateAllowedCharatcers("-"),
                is(equalTo("\\Q-;?:@&=+$,/.!~*'()\\E")));
    }

    @Test
    @Ignore
    public void replaceSymbols() {
    }

    @Test
    public void replaceCharacters_lastCharWasSymbol() {
        assertThat(sut.replaceCharacters(true, null), is(equalTo("")));
        assertThat(sut.replaceCharacters(true, ""), is(equalTo("")));
        assertThat(sut.replaceCharacters(true, "ß"), is(equalTo(" ss")));
        assertThat(sut.replaceCharacters(true, "α"), is(equalTo(" a")));
    }

    @Test
    public void replaceCharacters_lastCharWasNotSymbol() {
        assertThat(sut.replaceCharacters(false, null), is(equalTo("")));
        assertThat(sut.replaceCharacters(false, "ß"), is(equalTo("ss")));
        assertThat(sut.replaceCharacters(false, "α"), is(equalTo("a")));
    }

    @Test
    @Ignore
    public void replaceLanguageCharacters() {
    }

    @Test
    public void replaceNotAllowedCharacters() {
        assertThat(sut.replaceNotAllowedCharacters("", "", ""), is(equalTo("")));
        assertThat(sut.replaceNotAllowedCharacters("", "", "-"), is(equalTo("")));

        assertThat(sut.replaceNotAllowedCharacters("foo bar baz", "", "-"), is(equalTo("foo bar baz")));
        assertThat(sut.replaceNotAllowedCharacters("foo bar-baz", "", "-"), is(equalTo("foo bar-baz")));
        assertThat(sut.replaceNotAllowedCharacters("foo bar+baz", "", "-"), is(equalTo("foo bar-baz")));

        assertThat(sut.replaceNotAllowedCharacters("foo bar baz", "+", "-"), is(equalTo("foo bar baz")));
        assertThat(sut.replaceNotAllowedCharacters("foo bar-baz", "+", "-"), is(equalTo("foo bar-baz")));
        assertThat(sut.replaceNotAllowedCharacters("foo bar+baz", "+", "-"), is(equalTo("foo bar+baz")));
    }

    @Test
    public void cleanupReplacements() {
        assertThat(sut.cleanupReplacements("", "-"), is(equalTo("")));
        assertThat(sut.cleanupReplacements("foo", "-"), is(equalTo("foo")));
        assertThat(sut.cleanupReplacements("foo bar", "-"), is(equalTo("foo-bar")));
        assertThat(sut.cleanupReplacements("-foo bar-", "-"), is(equalTo("foo-bar")));
        assertThat(sut.cleanupReplacements("-foo  bar-", "-"), is(equalTo("foo-bar")));
        assertThat(sut.cleanupReplacements("---foo  bar---", "-"), is(equalTo("foo-bar")));
        assertThat(sut.cleanupReplacements("---foo  bar-baz---", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.cleanupReplacements("---foo  bar--baz---", "-"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void replaceWhitespaces() {
        assertThat(sut.replaceWhitespaces("", "-"), is(equalTo("")));
        assertThat(sut.replaceWhitespaces("foobarbaz", "-"), is(equalTo("foobarbaz")));
        assertThat(sut.replaceWhitespaces("foo bar baz", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.replaceWhitespaces("foo  bar  baz", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.replaceWhitespaces("foo   bar   baz", "-"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void replaceDuplicateSeparators() {
        assertThat(sut.replaceDuplicateSeparators("", "-"), is(equalTo("")));
        assertThat(sut.replaceDuplicateSeparators("foobarbaz", "-"), is(equalTo("foobarbaz")));
        assertThat(sut.replaceDuplicateSeparators("foo-bar-baz", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.replaceDuplicateSeparators("foo--bar--baz", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.replaceDuplicateSeparators("foo---bar---baz", "-"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void replaceLeadingAndTrailingSeparator() {
        assertThat(sut.replaceLeadingAndTrailingSeparator("", "-"), is(equalTo("")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("foo", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("-foo", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("--foo", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("---foo", "-"), is(equalTo("foo")));

        assertThat(sut.replaceLeadingAndTrailingSeparator("foo-", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("foo--", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("foo---", "-"), is(equalTo("foo")));

        assertThat(sut.replaceLeadingAndTrailingSeparator("-foo-", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("--foo--", "-"), is(equalTo("foo")));
        assertThat(sut.replaceLeadingAndTrailingSeparator("---foo---", "-"), is(equalTo("foo")));
    }
}
