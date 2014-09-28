package de.weltraumschaf.speakingurl;

import de.weltraumschaf.speakingurl.Slug.Builder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Tests for {@link Slug.Builder}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class BuilderTest {

    private final Builder sut = Slug.Builder.newBuiler();

    @Test
    public void newBuiler_alwaysNewInstance() {
        final Builder b1 = Slug.Builder.newBuiler();
        final Builder b2 = Slug.Builder.newBuiler();
        final Builder b3 = Slug.Builder.newBuiler();

        assertThat(b1, is(not(nullValue())));
        assertThat(b2, is(not(nullValue())));
        assertThat(b3, is(not(nullValue())));

        assertThat(b1, is(not(sameInstance(b2))));
        assertThat(b1, is(not(sameInstance(b3))));
        assertThat(b2, is(not(sameInstance(b1))));
        assertThat(b2, is(not(sameInstance(b3))));
        assertThat(b3, is(not(sameInstance(b1))));
        assertThat(b3, is(not(sameInstance(b2))));
    }

    @Test
    public void create_default() {
        final Slug product = sut.create();

        assertThat(product, is(not(nullValue())));
        assertThat(product.getCustom(), is(equalTo((Map<String, String>) new HashMap<String, String>())));
        assertThat(product.lang(), is(equalTo(Language.ENGLISH)));
        assertThat(product.maintainCase(), is(equalTo(false)));
        assertThat(product.mark(), is(equalTo(false)));
        assertThat(product.separator(), is(equalTo("-")));
        assertThat(product.titleCase(), is(equalTo(false)));
        assertThat(product.titleCaseExclude(), is(equalTo(Collections.<String>emptySet())));
        assertThat(product.truncate(), is(equalTo(0)));
        assertThat(product.uric(), is(equalTo(false)));
        assertThat(product.uricNoSlash(), is(equalTo(false)));
    }

    @Test
    public void create_withSeparator() {
        final Slug product = sut.separator("_").create();

        assertThat(product.separator(), is(equalTo("_")));
    }

    @Test(expected = NullPointerException.class)
    public void create_withNullSeparator() {
        sut.separator(null);
    }

    @Test
    public void create_withLang() {
        final Slug product = sut.lang(Language.NONE).create();

        assertThat(product.lang(), is(equalTo(Language.NONE)));
    }

    @Test(expected = NullPointerException.class)
    public void create_withNullLang() {
        sut.lang(null);
    }

    @Test
    public void create_withMaintainCase() {
        final Slug product = sut.maintainCase(true).create();

        assertThat(product.maintainCase(), is(equalTo(true)));
    }

    @Test
    public void create_withTitleCase() {
        final Slug product = sut.titleCase(true).create();

        assertThat(product.titleCase(), is(equalTo(true)));
    }

    @Test
    public void create_withTitleCaseExclude_varArg() {
        final Slug product = sut.titleCaseExclude("foo", "bar", "baz").create();

        assertThat(product.titleCaseExclude(),
            is(equalTo((Set<String>) new HashSet<>(Arrays.asList("foo", "bar", "baz")))));
    }

    @Test(expected = NullPointerException.class)
    public void create_withNullTitleCaseExclude_varArg() {
        sut.titleCaseExclude((String[]) null);
    }

    @Test
    public void create_withTitleCaseExclude_set() {
        final Slug product = sut.titleCaseExclude(
                new HashSet<>(Arrays.asList("foo", "bar", "baz"))).create();

        assertThat(product.titleCaseExclude(),
            is(equalTo((Set<String>) new HashSet<>(Arrays.asList("foo", "bar", "baz")))));
    }

    @Test(expected = NullPointerException.class)
    public void create_withNullTitleCaseExclude_set() {
        sut.titleCaseExclude((Set<String>) null);
    }

    @Test
    public void create_withTruncate() {
        final Slug product = sut.truncate(23).create();

        assertThat(product.truncate(), is(equalTo(23)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_withNegativeTruncate() {
        sut.truncate(-1);
    }

    @Test
    public void create_withUric() {
        final Slug product = sut.uric(true).create();

        assertThat(product.uric(), is(equalTo(true)));
    }

    @Test
    public void create_withUricNoSlash() {
        final Slug product = sut.uricNoSlash(true).create();

        assertThat(product.uricNoSlash(), is(equalTo(true)));
    }

    @Test
    public void create_withMark() {
        final Slug product = sut.mark(true).create();

        assertThat(product.mark(), is(equalTo(true)));
    }

    @Test
    public void create_withCustom() {
        final Map<String, String> custom = new HashMap<>();
        custom.put("foo", "bar");
        custom.put("baz", "baz");

        final Slug product = sut.custom(custom).create();

        assertThat(product.getCustom(), is(equalTo(custom)));
    }
}
