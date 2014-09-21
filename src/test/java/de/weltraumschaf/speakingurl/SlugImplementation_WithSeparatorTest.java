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
public class SlugImplementation_WithSeparatorTest {

    private final Slug sut = Slug.Builder.newBuiler().separator("_").create();

    @Test
    public void withTruncate() {
        assertThat(sut.get("Foo* Foobar FooBarBaz"), is(equalTo("foo_foobar_foobarbaz")));
    }

    @Test
    public void shouldNotFailIfSymbolAtTheEnd() {
        assertThat(sut.get("test &"), is(equalTo("test_and")));
        assertThat(sut.get("test & "), is(equalTo("test_and")));
        assertThat(sut.get("test ♥"), is(equalTo("test_love")));
        assertThat(sut.get("test ♥ "), is(equalTo("test_love")));
        assertThat(sut.get("test ♥  "), is(equalTo("test_love")));
    }

}
