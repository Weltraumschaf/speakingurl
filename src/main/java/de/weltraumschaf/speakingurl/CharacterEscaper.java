/*
 *  LICENSE
 *
 * "THE BEER-WARE LICENSE" (Revision 43):
 * "Sven Strittmatter" <weltraumschaf@googlemail.com> wrote this file.
 * As long as you retain this notice you can do whatever you want with
 * this stuff. If we meet some day, and you think this stuff is worth it,
 * you can buy me a non alcohol-free beer in return.
 * 
 * Copyright (C) 2012 "Sven Strittmatter" <weltraumschaf@googlemail.com>
 */
package de.weltraumschaf.speakingurl;

/**
 *
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
