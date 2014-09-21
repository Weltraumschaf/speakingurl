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
public class SlugImplementation_WithSeparator {

    @Test
    public void shouldSeparateWithNonWhitespace() {
        Slug sut = Slug.Builder.newBuiler().separator("-").create();
        assertThat(sut.get("Foo Bar Baz"), is(equalTo("foo-bar-baz")));

        sut = Slug.Builder.newBuiler().separator("*").create();
        assertThat(sut.get("Foo Bar Baz"), is(equalTo("foo*bar*baz")));

        sut = Slug.Builder.newBuiler().separator("_").create();
        assertThat(sut.get("Foo Bar Baz"), is(equalTo("foo_bar_baz")));

        sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get("Foo Bar Baz", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("Foo Bar Baz", "*"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("Foo Bar Baz", "_"), is(equalTo("foo_bar_baz")));
    }

    @Test
    public void shouldSeparateWithNonWhitespaceWithLeadingAndTrailingSpaces() {
        Slug sut = Slug.Builder.newBuiler().separator("-").create();
        assertThat(sut.get(" Foo Bar Baz "), is(equalTo("foo-bar-baz")));

        sut = Slug.Builder.newBuiler().separator("*").create();
        assertThat(sut.get("  Foo Bar Baz  "), is(equalTo("foo*bar*baz")));

        sut = Slug.Builder.newBuiler().separator("_").create();
        assertThat(sut.get("   Foo Bar Baz   "), is(equalTo("foo_bar_baz")));

        sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get(" Foo Bar Baz ", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("  Foo Bar Baz  ", "*"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("   Foo Bar Baz   ", "_"), is(equalTo("foo_bar_baz")));
    }

    @Test
    public void shouldSeparateWithTrailingSeparatorDash() {
        Slug sut = Slug.Builder.newBuiler().separator("-").create();
        assertThat(sut.get("-Foo Bar Baz-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("--Foo Bar Baz---"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("---Foo Bar Baz---"), is(equalTo("foo-bar-baz")));

        sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get("-Foo Bar Baz-", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("--Foo Bar Baz---", "-"), is(equalTo("foo-bar-baz")));
        assertThat(sut.get("---Foo Bar Baz---", "-"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void shouldSeparateWithTrailingSeparatorAsterisk() {
        Slug sut = Slug.Builder.newBuiler().separator("*").create();
        assertThat(sut.get("*Foo Bar Baz*"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("**Foo Bar Baz**"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("***Foo Bar Baz***"), is(equalTo("foo*bar*baz")));

        sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get("*Foo Bar Baz*", "*"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("**Foo Bar Baz**", "*"), is(equalTo("foo*bar*baz")));
        assertThat(sut.get("***Foo Bar Baz***", "*"), is(equalTo("foo*bar*baz")));
    }

    @Test
    public void shouldSeparateWithTrailingSeparatorUnderscore() {
        Slug sut = Slug.Builder.newBuiler().separator("_").create();
        assertThat(sut.get("_Foo Bar Baz_"), is(equalTo("foo_bar_baz")));
        assertThat(sut.get("__Foo Bar Baz__"), is(equalTo("foo_bar_baz")));
        assertThat(sut.get("___Foo Bar Baz___"), is(equalTo("foo_bar_baz")));

        sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get("_Foo Bar Baz_", "_"), is(equalTo("foo_bar_baz")));
        assertThat(sut.get("__Foo Bar Baz__", "_"), is(equalTo("foo_bar_baz")));
        assertThat(sut.get("___Foo Bar Baz___", "_"), is(equalTo("foo_bar_baz")));
    }

    @Test
    public void shouldRemoveTrailingSeparatorAsterisk() {
        Slug sut = Slug.Builder.newBuiler().separator("*").create();
        assertThat(sut.get(" C\"est un beau titre qui ne laisse rien à désirer !"),
                is(equalTo("c*est*un*beau*titre*qui*ne*laisse*rien*a*desirer")));

        sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get(" C\"est un beau titre qui ne laisse rien à désirer !", "*"),
                is(equalTo("c*est*un*beau*titre*qui*ne*laisse*rien*a*desirer")));
    }

}
