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
     * Default language ({@value #DEFAULT}).
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

    /**
     * Get the mapping for given language.
     *
     * <p>
     * If the given language has no mapping, then the mapping for {@link #DEFAULT}
     * will be returned.
     * </p>
     *
     * @param lang the get the mapping for
     * @return never {@code null}, immutable
     */
    Map<String, String> map(final Language lang) {
        if (MAPPING.containsKey(lang)) {
            return MAPPING.get(lang);
        }

        return MAPPING.get(DEFAULT);
    }

}
