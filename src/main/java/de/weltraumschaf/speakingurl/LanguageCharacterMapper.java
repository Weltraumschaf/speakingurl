package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Language specific characters translations
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class LanguageCharacterMapper {

    private static final Language DEFAULT = Language.ENGLISH;
    private static final Map<Language, Map<String, String>> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Language.ENGLISH, Collections.<String, String>emptyMap());

        final Map<String, String> swedish = new HashMap<>();
        swedish.put("ä", "a");
        swedish.put("Ä", "A");
        MAPPING.put(Language.SWEDISH, Collections.unmodifiableMap(swedish));
    }

    Map<String, String> map(Language lang) {
        if (MAPPING.containsKey(lang)) {
            return MAPPING.get(lang);
        }

        return MAPPING.get(DEFAULT);
    }

}
