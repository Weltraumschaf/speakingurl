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
public class SlugImplementation_WithMaintainCaseTest {

    private final Slug sut = Slug.Builder.newBuiler().maintainCase(true).create();

    @Test
    public void shouldMaintainCasecharacters() {
        assertThat(sut.get("Foo Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo, Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo- Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo] Bar Baz"), is(equalTo("Foo-Bar-Baz")));
        assertThat(sut.get("Foo > Bar ♥ Baz"), is(equalTo("Foo-greater-than-Bar-love-Baz")));
    }

}
