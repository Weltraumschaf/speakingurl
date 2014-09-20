package de.weltraumschaf.speakingurl;

/**
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class CharacterEscaper {
    private static final String REGEX = "[-\\\\^$*+?.()|\\[\\]{}\\/]";
    private static final String REPLACEMENT = "\\\\$&";
    
    private final Validator validator = new Validator();
    
    String escape(final String input) {
        return validator.notNull(input, "input").replaceAll(REGEX, REPLACEMENT);
    }
    
}
