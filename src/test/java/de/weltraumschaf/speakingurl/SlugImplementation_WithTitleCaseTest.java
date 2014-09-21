package de.weltraumschaf.speakingurl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
public class SlugImplementation_WithTitleCaseTest {

    @Test
    public void shouldTitleCaseTheCharacters() {
        final Slug sut = Slug.Builder.newBuiler().titleCase(true).create();

        assertThat(sut.get("This is big foo"), is(equalTo("This-Is-Big-Foo")));
        assertThat(sut.get("This is Big foo"), is(equalTo("This-Is-Big-Foo")));
        assertThat(sut.get("Don't drink and drive"), is(equalTo("Don-t-Drink-And-Drive")));
    }

    @Test
    public void shouldTitleCaseTheCharactersWithCustomArray() {
        Slug sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude("and", "yet").create();
        assertThat(sut.get("This is yet foo and bar"), is(equalTo("This-Is-yet-Foo-and-Bar")));

        sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude("a", "an", "and").create();
        assertThat(sut.get("This is a foo and an angry bird"), is(equalTo("This-Is-a-Foo-and-an-Angry-Bird")));

        sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude("a").create();
        assertThat(sut.get("This is a foo and an angry bird show"), is(equalTo("This-Is-a-Foo-And-An-Angry-Bird-Show")));

        sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude("and").create();
        assertThat(sut.get("Don't drink and drive"), is(equalTo("Don-t-Drink-and-Drive")));

        sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude(Collections.<String>emptySet()).create();
        assertThat(sut.get("Don't drink and drive"), is(equalTo("Don-t-Drink-And-Drive")));

        sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude(new HashSet<>(Arrays.asList("drink", "drive"))).create();
        assertThat(sut.get("Don't drink and drive"), is(equalTo("Don-t-drink-And-drive")));

        sut = Slug.Builder.newBuiler().titleCase(true).titleCaseExclude(new HashSet<>(Arrays.asList("42"))).create();
        assertThat(sut.get("Don't drink and drive"), is(equalTo("Don-t-Drink-And-Drive")));
    }

}
