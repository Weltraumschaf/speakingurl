package de.weltraumschaf.speakingurl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Tests for {@link SlugImplementation}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementation_DefaultTest {

    private final Slug sut = Slug.Builder.newBuiler().create();

    @Test
    public void shouldReturnEmptyForNull() {
        assertThat(sut.get(null), is(equalTo("")));
    }

    @Test
    public void shouldReturnEmptyForEmpty() {
        assertThat(sut.get(""), is(equalTo("")));
    }

    @Test
    public void shouldReturnEmptyForSpaces() {
        assertThat(sut.get("     "), is(equalTo("")));
    }

    @Test
    public void shouldReplaceWhitespacesWithSeparator() {
        assertThat(sut.get("foo bar baz"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void shouldLowercaseCharacters() {
        assertThat(sut.get("Foo Bar Baz"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void shouldRemoveLEadingAndTrailingSpaceIfAny() {
        assertThat(sut.get(" foo bar baz "), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void shouldRemoveMultipleWhitespacesAndLowercaseAll() {
        assertThat(sut.get(" foo bar  baz   FOO    BAR      BAZ      "), is(equalTo("foo-bar-baz-foo-bar-baz")));
    }

    @Test
    public void shouldRemoveMultipleSeparatorsAtStartAndEnd() {
        assertThat(sut.get("-foo- bar -baz-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("--foo- bar -baz---"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("---foo- bar -baz---"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void shouldRemoveMultpleSeparators() {
        assertThat(sut.get("foo- bar -baz"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void shouldRemoveNonBase64Characters() {
        for (char c : new char[] {'[', ']', ',', '*', '+', '~', '.', '(', ')', '\'', '"', '!', ':', '@'}) {
            assertThat(sut.get("foo " + c + " bar baz"), is(equalTo("foo-bar-baz")));
        }
    }

    @Test
    public void shouldRemoveTrailingSpacesAndMarkAndFrenchDiacritics() {
        assertThat(sut.get("C'est un beau titre qui ne laisse rien à désirer  ! "),
                is(equalTo("c-est-un-beau-titre-qui-ne-laisse-rien-a-desirer")));
    }

    @Test
    public void shouldHandleWhitespaceAfterSymbol() {
        assertThat(sut.get("∆299"), is(equalTo("delta-299")));
        assertThat(sut.get("∆world"), is(equalTo("delta-world")));
        assertThat(sut.get("∆-299"), is(equalTo("delta-299")));
        assertThat(sut.get("∆-world"), is(equalTo("delta-world")));
        assertThat(sut.get("(∆)299"), is(equalTo("delta-299")));
        assertThat(sut.get("∆-world"), is(equalTo("delta-world")));
        assertThat(sut.get("Hello∆299"), is(equalTo("hello-delta-299")));
        assertThat(sut.get("299∆Hello"), is(equalTo("299-delta-hello")));
    }

    @Test
    public void shouldNotFailIfSymbolAtTheEnd() {
        assertThat(sut.get("test &"), is(equalTo("test-and")));
        assertThat(sut.get("test & "), is(equalTo("test-and")));
        assertThat(sut.get("test ♥"), is(equalTo("test-love")));
        assertThat(sut.get("test ♥ "), is(equalTo("test-love")));
        assertThat(sut.get("test ♥  "), is(equalTo("test-love")));
    }

}
