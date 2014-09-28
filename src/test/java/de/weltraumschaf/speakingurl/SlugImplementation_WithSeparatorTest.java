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
public class SlugImplementation_WithSeparatorTest {

    private final Slug sut = Slug.Builder.newBuiler().separator("_").create();

    @Test
    public void withTruncate() {
        assertThat(sut.get("Foo* Foobar FooBarBaz"), is(equalTo("foo_foobar_foobarbaz")));
    }

    @Test
    @Ignore
    public void shouldNotFailIfAndAtTheEnd() {
        assertThat(sut.get("test &"), is(equalTo("test_and")));
    }

    @Test
    @Ignore
    public void shouldNotFailIfAndAtTheEndAndWhitespace() {
        assertThat(sut.get("test & "), is(equalTo("test_and")));
    }

    @Test
    @Ignore
    public void shouldNotFailIfLoveAtTheEnd() {
        assertThat(sut.get("test ♥"), is(equalTo("test_love")));
    }

    @Test
    @Ignore
    public void shouldNotFailIfLoveAtTheEndAndWhitespace() {
        assertThat(sut.get("test ♥ "), is(equalTo("test_love")));
    }

    @Test
    @Ignore
    public void shouldNotFailIfLoveAtTheEndAndWhitespaces() {
        assertThat(sut.get("test ♥  "), is(equalTo("test_love")));
    }

}
