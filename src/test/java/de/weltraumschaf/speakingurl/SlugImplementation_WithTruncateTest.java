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
public class SlugImplementation_WithTruncateTest {

    @Test
    public void truncateTo15() {
        final Slug sut = Slug.Builder.newBuiler().truncate(15).create();

        assertThat(sut.get("Foo* Foobar FooBarBaz"), is(equalTo("foo-foobar")));
    }

    @Test
    public void shouldMaintainCaseCharactersWithSmartTruncate() {
        Slug sut = Slug.Builder.newBuiler().truncate(12).create();
        assertThat(sut.get("Foobarbaz, Bar Baz"), is(equalTo("foobarbaz")));

        sut = Slug.Builder.newBuiler().truncate(15).create();
        assertThat(sut.get("Foobarbaz, Bar Baz"), is(equalTo("'foobarbaz-bar")));

        sut = Slug.Builder.newBuiler().truncate(15).create();
        assertThat(sut.get(" Foobarbaz, Bar Baz"), is(equalTo("foobarbaz-bar")));

        sut = Slug.Builder.newBuiler().truncate(15).create();
        assertThat(sut.get("  Foobarbaz,    Bar Baz"), is(equalTo("foobarbaz-bar")));

        sut = Slug.Builder.newBuiler().truncate(15).create();
        assertThat(sut.get("Foo Foo bar Zoo Bar Baz"), is(equalTo("foo-foo-bar-zoo")));

        sut = Slug.Builder.newBuiler().truncate(15).create();
        assertThat(sut.get("Foo Foo bar ZooBar Baz"), is(equalTo("foo-foo-bar")));

        sut = Slug.Builder.newBuiler().truncate(15).create();
        assertThat(sut.get("Foo Foo bar ZooBar Baz"), is(equalTo("foo-foo-bar")));

        sut = Slug.Builder.newBuiler().truncate(0).create();
        assertThat(sut.get("Foo Foo Bar Bar"), is(equalTo("foo-foo-bar-bar")));

        assertThat(sut.get("Foo Foo Bar Bar"), is(equalTo("foo-foo-bar-bar")));

        assertThat(sut.get("a Foo"), is(equalTo("a-foo")));
    }

}
