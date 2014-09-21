package de.weltraumschaf.speakingurl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Tests for {@link SlugImplementation}.
 *
 * See {@link http://tools.ietf.org/html/rfc3986 RFC for URIs}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementation_Rfc3986Test {

    @Test
    public void uricCharactersAllowed() {
        final Slug sut = Slug.Builder.newBuiler().uric(true).create();

        for (final char c : new char[]{';', '?', ':', '@', '&', '=', '+', ',', '/'}) {
            assertThat(sut.get("foo " + c + " bar baz"), is(equalTo("foo-" + c + "-bar-baz")));
        }
    }

    @Test
    public void uricNoSlashCharactersAllowed() {
        final Slug sut = Slug.Builder.newBuiler().uricNoSlash(true).create();

        for (final char c : new char[]{';', '?', ':', '@', '&', '=', '+', ','}) {
            assertThat(sut.get("foo " + c + " bar baz"), is(equalTo("foo-" + c + "-bar-baz")));
        }
    }

    @Test
    public void markCharactersAllowed() {
        final Slug sut = Slug.Builder.newBuiler().mark(true).create();

        for (final char c : new char[]{'.', '!', '~', '*', '\'', '(', ')'}) {
            assertThat(sut.get("foo " + c + " bar baz"),
                    is(equalTo("foo-" + c + "-bar-baz")));
        }
    }

    @Test
    public void uricCharactersAllowedSeparatorSemicolon() {
        final Slug sut = Slug.Builder.newBuiler().separator(";").create();

        for (final char c : new char[]{'?', ':', '@', '&', '=', '+', ',', '/'}) {
            assertThat(sut.get("foo " + c + " bar baz"),
                    is(equalTo("foo;" + c + ";bar;baz")));
        }
    }

    @Test
    public void uricCharactersAllowedSeparatorSemicolonIncludedInInputString() {
        final Slug sut = Slug.Builder.newBuiler().uric(true).separator(";").create();

        assertThat(sut.get("foo ; bar baz"),
                is(equalTo("foo;bar;baz")));
    }

    @Test
    public void uricNoSlashCharactersAllowedSeparatorSemicolon() {
        final Slug sut = Slug.Builder.newBuiler().uricNoSlash(true).separator(";").create();

        for (final char c : new char[]{'?', ':', '@', '&', '=', '+', ','}) {
            assertThat(sut.get("foo " + c + " bar baz"), is(equalTo("foo;" + c + ";bar;baz")));
        }
    }

    @Test
    public void uricNoSlashCharactersAllowedSeparatorSemicolonIncludedInInputString() {
        final Slug sut = Slug.Builder.newBuiler().uric(true).separator(";").create();

        assertThat(sut.get("foo ; bar baz"), is(equalTo("foo;bar;baz")));
    }

    @Test
    public void markCharactersAllowedSeparatorDot() {
        final Slug sut = Slug.Builder.newBuiler().mark(true).separator(".").create();

        for (final char c : new char[]{'!', '~', '*', '\'', '(', ')'}) {
            assertThat(sut.get("foo " + c + " bar baz"), is(equalTo("foo." + c + ".bar.baz")));
        }
    }

    @Test
    public void markCharactersAllowedSeparatorDotIncludedInInputString() {
        final Slug sut = Slug.Builder.newBuiler().uric(true).separator(".").create();

        assertThat(sut.get("foo . bar baz"), is(equalTo("foo.bar.baz")));
    }
}
