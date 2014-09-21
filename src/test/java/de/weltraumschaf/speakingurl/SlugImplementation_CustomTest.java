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
public class SlugImplementation_CustomTest {

    @Test
    public void withCustomCharsReplacement() {
        final Slug sut = Slug.Builder.newBuiler().custom(new String[][]{{"*", "o"}}).create();

        assertThat(sut.get("xyl*ph*n"), is(equalTo("xylophon")));
    }

    @Test
    public void withCustomCharsReplacementWithNotAllowedTargetChar() {
        final Slug sut = Slug.Builder.newBuiler().custom(new String[][]{{"o", "*"}}).create();

        assertThat(sut.get("xylophon"), is(equalTo("xyl-ph-n")));
    }

    @Test
    public void withCustomCharsReplacementWithAllowedTargetCharOptionMark() {
        final Slug sut = Slug.Builder.newBuiler().custom(new String[][]{{"o", "*"}}).mark(true).create();

        assertThat(sut.get("xylophon"), is(equalTo("xyl*ph*n")));
    }

    @Test
    public void withCustomCharsReplacementWithOptionMark() {
        final Slug sut = Slug.Builder.newBuiler().custom(new String[][]{{"*", "o"}}).mark(true).create();

        assertThat(sut.get("xyl*ph*n"), is(equalTo("xylophon")));
    }

    @Test
    public void withCustomCharToStringReplacement() {
        final Slug sut = Slug.Builder.newBuiler()
            .custom(new String[][]{
                {"*", "STAR"},
                {"q", "qqq"},
                {"and", ""},
                {"or", ""}
            })
            .create();

        assertThat(sut.get("xyl*ph*n"), is(equalTo("xylstarphstarn")));
        assertThat(sut.get("quack"), is(equalTo("qqquack")));
        assertThat(sut.get("Foo and Bar or Baz"), is(equalTo("foo-bar-baz")));
    }

    @Test
    public void withCustomStringReplacement() {
        final Slug sut = Slug.Builder.newBuiler()
            .custom(new String[][]{
                {"and", "und"},
                {"or", "oder"},
                {"*", " and "}
            })
            .create();

        assertThat(sut.get("bus and train"), is(equalTo("bus-und-train")));
        assertThat(sut.get("bus or train"), is(equalTo("bus-oder-train")));
        assertThat(sut.get("busandtrain"), is(equalTo("busandtrain")));
        assertThat(sut.get("busortrain"), is(equalTo("busortrain")));
        assertThat(sut.get("bus*train"), is(equalTo("bus-and-train")));

        assertThat(sut.get("bus and train bus and train"), is(equalTo("bus-und-train-bus-und-train")));
        assertThat(sut.get("bus or train bus or train"), is(equalTo("bus-oder-train-bus-oder-train")));
        assertThat(sut.get("busandtrain busandtrain"), is(equalTo("busandtrain-busandtrain")));
        assertThat(sut.get("busortrain busortrain"), is(equalTo("busortrain-busortrain")));
    }

    @Test
    public void withCustomStringReplacementWithOptionMark() {
        final Slug sut = Slug.Builder.newBuiler()
            .custom(new String[][]{
                {"*", "STAR"},
                {"q", "qqq"},
                {"z", ""}
            })
            .mark(true)
            .create();

        assertThat(sut.get("xyl*ph*n"), is(equalTo("xylstarphstarn")));
        assertThat(sut.get("qxxx"), is(equalTo("qqqxxx")));
        assertThat(sut.get("xxxqxxx"), is(equalTo("xxxqqqxxx")));
        assertThat(sut.get("qqq"), is(equalTo("qqqqqqqqq")));
        assertThat(sut.get("*q*"), is(equalTo("starqqqstar")));
        assertThat(sut.get("zoo"), is(equalTo("oo")));
        assertThat(sut.get("zooz"), is(equalTo("oo")));
    }

    @Test
    public void withCustomStringReplacementWithOptionMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler()
            .custom(new String[][]{
                {"*", "STAR"},
                {"q", "qqq"},
            })
            .maintainCase(true)
            .create();

        assertThat(sut.get("xyl*ph*n"), is(equalTo("xylSTARphSTARn")));
        assertThat(sut.get("qXXX"), is(equalTo("qqqXXX")));
        assertThat(sut.get("qqq"), is(equalTo("qqqqqqqqq")));
        assertThat(sut.get("*q*"), is(equalTo("STARqqqSTAR")));
    }
}
