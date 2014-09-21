package de.weltraumschaf.speakingurl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of slug.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SlugImplementation implements Slug {

    private static final Set<Character> URIC_NO_SLASH;
    private static final Set<Character> URIC;
    private static final Set<Character> MARK = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList('.', '!', '~', '*', '\'', '(', ')')));

    static {
        Set<Character> characters = new HashSet<>(Arrays.asList(';', '?', ':', '@', '&', '=', '+', '$', ','));
        URIC_NO_SLASH = Collections.unmodifiableSet(characters);

        characters.add('/');
        URIC = Collections.unmodifiableSet(characters);
    }

    private final Options options;

    private String allowedChars;

    public SlugImplementation(final Options options) {
        super();
        this.options = options;
    }

    @Override
    public String getSeparator() {
        return options.getSeparator();
    }

    @Override
    public Language getLang() {
        return options.getLang();
    }

    @Override
    public boolean isMaintainCase() {
        return options.isMaintainCase();
    }

    @Override
    public boolean isTitleCase() {
        return options.isTitleCase();
    }

    @Override
    public Set<String> getTitleCaseExclude() {
        return options.getTitleCaseExclude();
    }

    @Override
    public int getTruncate() {
        return options.getTruncate();
    }

    @Override
    public boolean isUric() {
        return options.isUric();
    }

    @Override
    public boolean isUricNoSlash() {
        return options.isUricNoSlash();
    }

    @Override
    public boolean isMark() {
        return options.isMark();
    }

    @Override
    public Map<String, String> getCustom() {
        return options.getCustom();
    }

    @Override
    public String get(final String input) {
        return get(input, options.getSeparator());
    }

    @Override
    public String get(final String input, final String separator) {
        if (null == input) {
            return "";
        }

        if (input.trim().isEmpty()) {
            return "";
        }

        final Map<String, String> customReplacements = new HashMap<>();
        String allowedChars = separator;

        if (options.isUric()) {
            allowedChars += join(URIC);
        }

        if (options.isUricNoSlash()) {
            allowedChars += join(URIC_NO_SLASH);
        }

        if (options.isMark()) {
            allowedChars += join(MARK);
        }

        throw new UnsupportedOperationException("Not implemented yet!");
    }

    private String join(final Set<?> s) {
        final StringBuilder buffer = new StringBuilder();

        for (final Object o : s) {
            buffer.append(o.toString());
        }

        return buffer.toString();
    }
}
