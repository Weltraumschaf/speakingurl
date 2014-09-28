package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link SlugImplementation}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementationTest {

    private final Options options = new Options();
    private final SlugImplementation sut = new SlugImplementation(options);

    @Before
    public void resetOptions() {
        options.resetToDefaults();
    }

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
        options.uric(true);

        assertThat(sut.generateAllowedCharatcers("-"), is(equalTo("\\Q-;?:@&=+$,/\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withUricWithoutSlash() {
        options.uricWithoutSlash(true);

        assertThat(sut.generateAllowedCharatcers("-"), is(equalTo("\\Q-;?:@&=+$,\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withMark() {
        options.mark(true);

        assertThat(sut.generateAllowedCharatcers("-"), is(equalTo("\\Q-.!~*'()\\E")));
    }

    @Test
    public void generateAllowedCharatcers_withUricUricwithoutSlashAndMark() {
        options.uric(true);
        options.uricWithoutSlash(true);
        options.mark(true);

        assertThat(sut.generateAllowedCharatcers("-"),
                is(equalTo("\\Q-;?:@&=+$,/.!~*'()\\E")));
    }

    @Test
    public void replaceSymbols_lastCharWasSymbolTrue() {
        assertThat(sut.replaceSymbols(null, true, null, null, null, 0), is(equalTo("")));
        assertThat(sut.replaceSymbols("", true, null, null, null, 0), is(equalTo("")));

        final String separator = "-";
        assertThat(sut.replaceSymbols("", true, null, separator, null, 0), is(equalTo("")));
        assertThat(sut.replaceSymbols("&", true, null, separator, null, 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", true, "", separator, null, 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", true, null, separator, "", 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", true, "", separator, "", 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", true, "result", separator, "", 0), is(equalTo("-and")));
        assertThat(sut.replaceSymbols("&", true, "result!", separator, "", 0), is(equalTo("-and")));
        assertThat(sut.replaceSymbols("&", true, "result", separator, "&foo", 0), is(equalTo("-and-")));
        assertThat(sut.replaceSymbols("&", true, "result", separator, "& foo", 0), is(equalTo("-and")));
    }

    @Test
    public void replaceSymbols_lastCharWasSymbolFalse() {
        assertThat(sut.replaceSymbols(null, false, null, null, null, 0), is(equalTo("")));
        assertThat(sut.replaceSymbols("", false, null, null, null, 0), is(equalTo("")));

        final String separator = "-";
        assertThat(sut.replaceSymbols("", false, null, separator, null, 0), is(equalTo("")));
        assertThat(sut.replaceSymbols("&", false, null, separator, null, 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", false, "", separator, null, 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", false, null, separator, "", 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", false, "", separator, "", 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", false, "result", separator, "", 0), is(equalTo("-and")));
        assertThat(sut.replaceSymbols("&", false, "result!", separator, "", 0), is(equalTo("and")));
        assertThat(sut.replaceSymbols("&", false, "result", separator, "&foo", 0), is(equalTo("-and-")));
        assertThat(sut.replaceSymbols("&", false, "result", separator, "& foo", 0), is(equalTo("-and")));
    }

    @Test
    public void replaceCharacters_lastCharWasSymbol() {
        assertThat(sut.replaceCharacters(true, null), is(equalTo("")));
        assertThat(sut.replaceCharacters(true, ""), is(equalTo("")));
        assertThat(sut.replaceCharacters(true, "ß"), is(equalTo("ss")));
        assertThat(sut.replaceCharacters(true, "α"), is(equalTo(" a")));
    }

    @Test
    public void replaceCharacters_lastCharWasNotSymbol() {
        assertThat(sut.replaceCharacters(false, null), is(equalTo("")));
        assertThat(sut.replaceCharacters(false, "ß"), is(equalTo("ss")));
        assertThat(sut.replaceCharacters(false, "α"), is(equalTo("a")));
    }

    @Test
    public void replaceLanguageCharacters_default_lastCharWasSymbol() {
        assertThat(sut.replaceLanguageCharacters(true, null), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(true, ""), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(true, "ä"), is(equalTo("ä")));
        assertThat(sut.replaceLanguageCharacters(true, "Ä"), is(equalTo("Ä")));
    }

    @Test
    public void replaceLanguageCharacters_default_lastCharWasNotSymbol() {
        assertThat(sut.replaceLanguageCharacters(false, null), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(false, ""), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(false, "ä"), is(equalTo("ä")));
        assertThat(sut.replaceLanguageCharacters(false, "Ä"), is(equalTo("Ä")));
    }

    @Test
    public void replaceLanguageCharacters_english_lastCharWasSymbol() {
        options.language(Language.ENGLISH);

        assertThat(sut.replaceLanguageCharacters(true, null), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(true, ""), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(true, "ä"), is(equalTo("ä")));
        assertThat(sut.replaceLanguageCharacters(true, "Ä"), is(equalTo("Ä")));
    }

    @Test
    public void replaceLanguageCharacters_english_lastCharWasNotSymbol() {
        options.language(Language.ENGLISH);

        assertThat(sut.replaceLanguageCharacters(false, null), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(false, ""), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(false, "ä"), is(equalTo("ä")));
        assertThat(sut.replaceLanguageCharacters(false, "Ä"), is(equalTo("Ä")));
    }

    @Test
    public void replaceLanguageCharacters_swedish_lastCharWasSymbol() {
        options.language(Language.SWEDISH);

        assertThat(sut.lang(), is(equalTo(Language.SWEDISH)));
        assertThat(sut.replaceLanguageCharacters(true, null), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(true, ""), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(true, "ä"), is(equalTo(" a")));
        assertThat(sut.replaceLanguageCharacters(true, "Ä"), is(equalTo(" A")));
    }

    @Test
    public void replaceLanguageCharacters_swedish_lastCharWasNotSymbol() {
        options.language(Language.SWEDISH);

        assertThat(sut.lang(), is(equalTo(Language.SWEDISH)));
        assertThat(sut.replaceLanguageCharacters(false, null), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(false, ""), is(equalTo("")));
        assertThat(sut.replaceLanguageCharacters(false, "ä"), is(equalTo("a")));
        assertThat(sut.replaceLanguageCharacters(false, "Ä"), is(equalTo("A")));
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

    @Test
    public void transformCase_emptyMap_titleCaseOff() {
        options.titleCase(true);

        assertThat(sut.transformCase(null, null), is(equalTo("")));
        assertThat(sut.transformCase("", null), is(equalTo("")));

        final Map<String, String> emptyMap = Collections.<String, String>emptyMap();
        assertThat(sut.transformCase(null, emptyMap), is(equalTo("")));
        assertThat(sut.transformCase("", emptyMap), is(equalTo("")));

        assertThat(sut.transformCase("This is big foo",emptyMap), is(equalTo("This Is Big Foo")));
        assertThat(sut.transformCase("This is Big foo",emptyMap), is(equalTo("This Is Big Foo")));
        assertThat(sut.transformCase("Don't drink and drive",emptyMap), is(equalTo("Don't Drink And Drive")));
    }

    @Test
    public void transformCase_emptyMap_titleCaseOn() {
        options.titleCase(true);

        assertThat(sut.transformCase(null, null), is(equalTo("")));
        assertThat(sut.transformCase("", null), is(equalTo("")));

        final Map<String, String> emptyMap = Collections.<String, String>emptyMap();
        assertThat(sut.transformCase(null, emptyMap), is(equalTo("")));
        assertThat(sut.transformCase("", emptyMap), is(equalTo("")));

        assertThat(sut.transformCase("This is big foo",emptyMap), is(equalTo("This Is Big Foo")));
        assertThat(sut.transformCase("This is Big foo",emptyMap), is(equalTo("This Is Big Foo")));
        assertThat(sut.transformCase("Don't drink and drive",emptyMap), is(equalTo("Don't Drink And Drive")));
    }

    @Test
    public void transformCase() {
        final Map<String, String> emptyMap = new HashMap<>();
        emptyMap.put("is", "is");
        emptyMap.put("foo", "foo");
        assertThat(sut.transformCase(null, emptyMap), is(equalTo("")));
        assertThat(sut.transformCase("", emptyMap), is(equalTo("")));

        assertThat(sut.transformCase("This is big foo",emptyMap), is(equalTo("This is Big foo")));
        assertThat(sut.transformCase("This is Big foo",emptyMap), is(equalTo("This is Big foo")));
        assertThat(sut.transformCase("Don't drink and drive",emptyMap), is(equalTo("Don't Drink And Drive")));
    }

    @Test
    public void ucfirst() {
        assertThat(sut.ucfirst(null), is(equalTo("")));
        assertThat(sut.ucfirst(""), is(equalTo("")));
        assertThat(sut.ucfirst("foobar"), is(equalTo("Foobar")));
        assertThat(sut.ucfirst("Foobar"), is(equalTo("Foobar")));
    }
}
