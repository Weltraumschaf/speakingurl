package de.weltraumschaf.speakingurl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests for {@link LanguageCharacterMapper}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class LanguageCharacterMapperTest {

    private final LanguageCharacterMapper sut = new LanguageCharacterMapper();

    private List<String> lcAlphabet() {
        return lcAlphabet(Collections.<String>emptyList());
    }


    private List<String> lcAlphabet(final List<String> excludes) {
        final List<String> chars = new ArrayList<>();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            final String str = String.valueOf(ch);

            if (excludes.contains(str)) {
                continue;
            }

            chars.add(str);
        }

        return chars;
    }

    private List<String> ucAlphabet() {
        return ucAlphabet(Collections.<String>emptyList());
    }

    private List<String> ucAlphabet(final List<String> excludes) {
        final List<String> chars = new ArrayList<>();

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            final String str = String.valueOf(ch);

            if (excludes.contains(str)) {
                continue;
            }

            chars.add(str);
        }

        return chars;
    }

    @Test
    public void knowsCharacter_none() {
        for (final String ch : lcAlphabet()) {
            assertThat(sut.knowsCharacter(Language.NONE, ch), is(equalTo(false)));
        }

        for (final String ch : ucAlphabet()) {
            assertThat(sut.knowsCharacter(Language.NONE, ch), is(equalTo(false)));
        }

        for (final String ch : Arrays.asList("ä", "Ä")) {
            assertThat(sut.knowsCharacter(Language.NONE, ch), is(equalTo(false)));
        }
    }

    @Test
    public void knowsCharacter_english() {
        for (final String ch : lcAlphabet()) {
            assertThat(sut.knowsCharacter(Language.ENGLISH, ch), is(equalTo(false)));
        }

        for (final String ch : ucAlphabet()) {
            assertThat(sut.knowsCharacter(Language.ENGLISH, ch), is(equalTo(false)));
        }

        for (final String ch : Arrays.asList("ä", "Ä")) {
            assertThat(sut.knowsCharacter(Language.ENGLISH, ch), is(equalTo(false)));
        }

    }

    @Test
    public void knowsCharacter_swedish() {
        final List<String> swedish = Arrays.asList("ä", "Ä");

        for (final String ch : lcAlphabet(swedish)) {
            assertThat(sut.knowsCharacter(Language.SWEDISH, ch), is(equalTo(false)));
        }

        for (final String ch : ucAlphabet(swedish)) {
            assertThat(sut.knowsCharacter(Language.SWEDISH, ch), is(equalTo(false)));
        }

        for (final String ch : swedish) {
            assertThat(sut.knowsCharacter(Language.SWEDISH, ch), is(equalTo(true)));
        }
    }

    @Test
    public void mapCharacter() {
        assertThat(sut.mapCharacter(Language.SWEDISH, "ä"), is(equalTo("a")));
        assertThat(sut.mapCharacter(Language.SWEDISH, "Ä"), is(equalTo("A")));
    }

}
