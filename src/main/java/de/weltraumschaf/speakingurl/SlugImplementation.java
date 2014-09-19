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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SlugImplementation implements Slug {

    static final Set<Character> URIC_NO_SLASH;
    static final Set<Character> URIC;
    static final Set<Character> MARK = Collections.unmodifiableSet(
        new HashSet<>(Arrays.asList('.', '!', '~', '*', '\'', '(', ')')));
        
    static {
        Set<Character> characters = new HashSet<>(Arrays.asList(';', '?', ':', '@', '&', '=', '+', '$', ','));
        URIC_NO_SLASH = Collections.unmodifiableSet(characters);
        
        characters.add('/');
        URIC = Collections.unmodifiableSet(characters);
    }
    
    String separator;
    Language lang;
    boolean maintainCase;
    boolean titleCase;
    String[] titleCaseExclude;
    int truncate;
    boolean uric;
    boolean uricNoSlash;
    boolean mark;
    String[][] custom;

    @Override
    public String get(final String input) {
        if (null == input) {
            throw new NullPointerException("Parameter 'input' must not be null!");
        }

        return "";
    }
}
