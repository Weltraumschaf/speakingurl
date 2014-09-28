package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Language specific characters translations.
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

    /**
     * Input validator.
     */
    private final Validator validator = new Validator();

    /**
     * Tells if there is a mapping for a particular language.
     *
     * @param lang must not be {@code null}
     * @param ch you want to map, must not be {@code null} or empty
     * @return {@code true} if the given character has a mapping, else {@code false}
     */
    boolean knowsCharacter(final Language lang, final String ch) {
        validator.notNull(lang, "validator");

        if (MAPPING.containsKey(lang)) {
            return MAPPING.get(lang).containsKey(ch);
        }

        return MAPPING.get(DEFAULT).containsKey(ch);
    }

    /**
     * Maps given character for a particular language.
     * <p>
     * Throws an {@link IllegalArgumentException} if {@link #knowsCharacter(de.weltraumschaf.speakingurl.Language,
     * java.lang.String)  there is no mapping}.
     * </p>
     *
     * @param lang must not be {@code null}
     * @param ch you want to map, must not be {@code null} or empty
     * @return never {@code null}
     */
    String mapCharacter(final Language lang, final String ch) {
        if (knowsCharacter(lang, ch)) {
            return MAPPING.get(lang).get(ch);
        }

        throw new IllegalArgumentException(String.format("Unknown character '%s' for language %s!", ch, lang));
    }

}
