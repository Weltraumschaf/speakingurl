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
public class SlugImplementation_WithUricNoSlashTest {

    private final Slug sut = Slug.Builder.newBuiler().uricNoSlash(true).create();

    @Test
    public void withUricNoSlash() {
        assertThat(sut.get("Foo/ Bar= Baz"), is(equalTo("foo-bar=-baz")));
    }

}
