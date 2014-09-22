package de.weltraumschaf.speakingurl;

import java.util.regex.Pattern;

/**
 * Escapes special characters used in regular expressions.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class CharacterEscaper {

    /**
     * Validates the input string.
     */
    private final Validator validator = new Validator();

    /**
     * Escapes the given input string.
     *
     * @param input must not be {@code null}
     * @return never {@code null} may be empty
     */
    String escape(final String input) {
        return Pattern.quote(validator.notNull(input, "input"));
    }

}
