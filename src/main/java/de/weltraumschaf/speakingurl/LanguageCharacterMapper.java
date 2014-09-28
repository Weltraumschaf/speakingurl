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

    /**
     * Default language.
     */
    private static final Language DEFAULT = Language.ENGLISH;
    /**
     * Holds the mapping.
     */
    private static final Map<Language, Map<String, String>> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Language.ENGLISH, Collections.<String, String>emptyMap());

        final Map<String, String> swedish = new HashMap<>();
        swedish.put("ä", "a");
        swedish.put("Ä", "A");

        MAPPING.put(Language.SWEDISH, Collections.unmodifiableMap(swedish));
    }

    boolean knowsCharacter(final Language lang, final String ch) {
        if (MAPPING.containsKey(lang)) {
            return MAPPING.get(lang).containsKey(ch);
        }

        return MAPPING.get(DEFAULT).containsKey(ch);
    }

    String mapCharacter(final Language lang, final String ch) {
        if (knowsCharacter(lang, ch)) {
            return MAPPING.get(lang).get(ch);
        }

        throw new IllegalArgumentException(String.format("Unknown character '%s' for language %s!", ch, lang));
    }

}
