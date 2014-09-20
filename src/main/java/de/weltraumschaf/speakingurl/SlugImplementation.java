package de.weltraumschaf.speakingurl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sascha Droste <pid@posteo.net>
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
