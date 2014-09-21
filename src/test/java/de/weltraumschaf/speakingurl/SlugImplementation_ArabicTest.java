package de.weltraumschaf.speakingurl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.assertThat;


/**
 * Tests for {@link SlugImplementation}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementation_ArabicTest {

    private final Slug sut = Slug.Builder.newBuiler().lang(Language.ARABIC).create();

    @Test
    public void translateArabicLetters() {
        assertThat(sut.get("بشس تاقفغقف  -  ت ب ي ق"), is(equalTo("bshs-taqfghqf-t-b-y-q")));
    }

}
