package de.weltraumschaf.speakingurl;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests for {@link CharacterEscaper}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class CharacterEscaperTest {

    private final CharacterEscaper sut = new CharacterEscaper();

    @Test(expected = NullPointerException.class)
    public void escapeNull() {
        sut.escape(null);
    }

    @Test
    public void escapeEmpty() {
        assertThat(sut.escape(""), is(equalTo("\\Q\\E")));
    }

    @Test
    public void escapeMinus() {
        assertThat(sut.escape("foo-bar"), is(equalTo("\\Qfoo-bar\\E")));
    }

    @Test
    public void escapePlus() {
        assertThat(sut.escape("foo+bar"), is(equalTo("\\Qfoo+bar\\E")));
    }

    @Test
    public void escapeAsterisk() {
        assertThat(sut.escape("foo*bar"), is(equalTo("\\Qfoo*bar\\E")));
    }

    @Test
    public void escapeBackslash() {
        assertThat(sut.escape("foo\\bar"), is(equalTo("\\Qfoo\\bar\\E")));
    }

    @Test
    public void escapeSlash() {
        assertThat(sut.escape("foo/bar"), is(equalTo("\\Qfoo/bar\\E")));
    }

    @Test
    public void escapeCaret() {
        assertThat(sut.escape("foo^bar"), is(equalTo("\\Qfoo^bar\\E")));
    }

    @Test
    public void escapeDollar() {
        assertThat(sut.escape("foo$bar"), is(equalTo("\\Qfoo$bar\\E")));
    }

    @Test
    public void escapePipe() {
        assertThat(sut.escape("foo|bar"), is(equalTo("\\Qfoo|bar\\E")));
    }

    @Test
    public void escapeQuestionmark() {
        assertThat(sut.escape("foo?bar"), is(equalTo("\\Qfoo?bar\\E")));
    }

    @Test
    public void escapeDot() {
        assertThat(sut.escape("foo.bar"), is(equalTo("\\Qfoo.bar\\E")));
    }

    @Test
    public void escapeLeftParen() {
        assertThat(sut.escape("foo(bar"), is(equalTo("\\Qfoo(bar\\E")));
    }

    @Test
    public void escapeRightParen() {
        assertThat(sut.escape("foo)bar"), is(equalTo("\\Qfoo)bar\\E")));
    }

    @Test
    public void escapeLeftBracket() {
        assertThat(sut.escape("foo[bar"), is(equalTo("\\Qfoo[bar\\E")));
    }

    @Test
    public void escapeRightBracket() {
        assertThat(sut.escape("foo]bar"), is(equalTo("\\Qfoo]bar\\E")));
    }

    @Test
    public void escapeLeftBrace() {
        assertThat(sut.escape("foo{bar"), is(equalTo("\\Qfoo{bar\\E")));
    }

    @Test
    public void escapeRightBrace() {
        assertThat(sut.escape("foo}bar"), is(equalTo("\\Qfoo}bar\\E")));
    }

}
