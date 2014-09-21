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
public class SlugImplementation_WithUricTest {

    private final Slug sut = Slug.Builder.newBuiler().uric(true).create();

    @Test
    public void withUric() {
        assertThat(sut.get(" :80:/Foo/Bar/Baz:Foo"), is(equalTo(":80:/foo/bar/baz:foo")));
    }

}
