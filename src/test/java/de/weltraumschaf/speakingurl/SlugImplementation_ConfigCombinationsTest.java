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
public class SlugImplementation_ConfigCombinationsTest {

    @Test
    public void shouldSeparateWithConfiguredCharacterWithNonBase64Separator() {
        final Slug sut = Slug.Builder.newBuiler().separator("*").maintainCase(true).create();

        assertThat(sut.get("Foo, Bar Baz"), is(equalTo("Foo*Bar*Baz")));
        assertThat(sut.get("Foo- Bar Baz"), is(equalTo("Foo-*Bar*Baz")));
        assertThat(sut.get("Foo] Bar Baz"), is(equalTo("Foo*Bar*Baz")));
    }

    @Test
    public void shouldSeparateWithConfiguredCharacterWithSmartTrim() {
        Slug sut = Slug.Builder.newBuiler().separator("_").truncate(12).create();
        assertThat(sut.get("Foobarbaz, Bar Baz"), is(equalTo("foobarbaz")));

        sut = Slug.Builder.newBuiler().separator("_").truncate(15).create();
        assertThat(sut.get("Foobarbaz, Bar Baz"), is(equalTo("foobarbaz_bar")));
        assertThat(sut.get(" Foobarbaz, Bar Baz"), is(equalTo("foobarbaz_bar")));
        assertThat(sut.get("  Foobarbaz,    Bar Baz"), is(equalTo("foobarbaz_bar")));
    }

    @Test
    public void shouldMaintainCaseCharactersWithNonBase64Separator() {
        final Slug sut = Slug.Builder.newBuiler().separator("*").mark(true).create();

        assertThat(sut.get("Foo, Bar Baz"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("Foo- Bar Baz"), is(equalTo("foo-*bar*baz")));
        assertThat(sut.get("Foo] Bar Baz"), is(equalTo("foo*bar*baz")));
    }

    @Test
    public void shouldMaintainCaseCharactersWithOnlyBase64CharactersAllowed() {
        Slug sut = Slug.Builder.newBuiler().maintainCase(true).uric(true).uricNoSlash(true).mark(true).create();
        assertThat(sut.get("Foo- Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo] Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo, Bar Baz"), is(equalTo("Foo,-Bar-Baz")));

        sut = Slug.Builder.newBuiler().maintainCase(true).uric(false).uricNoSlash(false).mark(false).create();
        assertThat(sut.get("Foo- Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo] Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo, Bar Baz"), is(equalTo("Foo-Bar-Baz")));
    }

    @Test
    public void shouldMaintainCaseCharactersWithSmartTrim() {
        Slug sut = Slug.Builder.newBuiler().maintainCase(true).truncate(12).create();
        assertThat(sut.get("Foobarbaz, Bar Baz"), is(equalTo("Foobarbaz")));

        sut = Slug.Builder.newBuiler().maintainCase(true).truncate(15).create();
        assertThat(sut.get("Foobarbaz, Bar Baz"), is(equalTo("Foobarbaz-Bar")));
        assertThat(sut.get(" Foobarbaz, Bar Baz"), is(equalTo("Foobarbaz-Bar")));
        assertThat(sut.get("  Foobarbaz,    Bar Baz"), is(equalTo("Foobarbaz-Bar")));
    }

    @Test
    public void shouldPreferBase64CharactersOnly() {
        final Slug sut = Slug.Builder.newBuiler().uric(false).uricNoSlash(false).mark(false).create();

        assertThat(sut.get("Foo, Bar Baz"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("Foo- Bar Baz"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("Foo] Bar Baz"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("Foo* Bar Baz"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void withMarkAndMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().mark(true).maintainCase(true).create();

        assertThat(sut.get("Foo* Bar Baz"), is(equalTo("Foo*-Bar-Baz")));
    }

}
