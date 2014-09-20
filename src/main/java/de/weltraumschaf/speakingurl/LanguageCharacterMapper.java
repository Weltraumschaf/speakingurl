package de.weltraumschaf.speakingurl;

import java.util.HashMap;
import java.util.Map;

/**
 * Language specific characters translations
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class LanguageCharacterMapper {

    private static final Map<Language, String[][]> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Language.ENGLISH, new String[0][0]);
        MAPPING.put(Language.SWEDISH, new String[][]{
            {"ä", "a"},
            {"Ä", "A"}
        });
    }
}
