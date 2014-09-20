package de.weltraumschaf.speakingurl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
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
    
    private String separator;
    private Language lang;
    private boolean maintainCase;
    private boolean titleCase;
    private Set<String> titleCaseExclude;
    private int truncate;
    private boolean uric;
    private boolean uricNoSlash;
    private boolean mark;
    private String[][] custom;

    @Override
    public String get(final String input) {
        if (null == input) {
            throw new NullPointerException("Parameter 'input' must not be null!");
        }

        return "";
    }

    @Override
    public String getSeparator() {
        return separator;
    }

    SlugImplementation setSeparator(final String separator) {
        this.separator = separator;
        return this;
    }

    @Override
    public Language getLang() {
        return lang;
    }

    SlugImplementation setLang(final Language lang) {
        this.lang = lang;
        return this;
    }

    @Override
    public boolean isMaintainCase() {
        return maintainCase;
    }

    SlugImplementation setMaintainCase(final boolean maintainCase) {
        this.maintainCase = maintainCase;
        return this;
    }

    @Override
    public boolean isTitleCase() {
        return titleCase;
    }

    SlugImplementation  setTitleCase(final boolean titleCase) {
        this.titleCase = titleCase;
        return this;
    }

    @Override
    public Set<String> getTitleCaseExclude() {
        return titleCaseExclude;
    }

    SlugImplementation  setTitleCaseExclude(final Set<String> titleCaseExclude) {
        this.titleCaseExclude = titleCaseExclude;
        return this;
    }

    @Override
    public int getTruncate() {
        return truncate;
    }

    SlugImplementation  setTruncate(final int truncate) {
        this.truncate = truncate;
        return this;
    }

    @Override
    public boolean isUric() {
        return uric;
    }

    SlugImplementation  setUric(final boolean uric) {
        this.uric = uric;
        return this;
    }

    @Override
    public boolean isUricNoSlash() {
        return uricNoSlash;
    }

    SlugImplementation  setUricNoSlash(final boolean uricNoSlash) {
        this.uricNoSlash = uricNoSlash;
        return this;
    }

    @Override
    public boolean isMark() {
        return mark;
    }

    SlugImplementation  setMark(final boolean mark) {
        this.mark = mark;
        return this;
    }

    @Override
    public String[][] getCustom() {
        return custom;
    }

    SlugImplementation  setCustom(final String[][] custom) {
        this.custom = custom;
        return this;
    }
    
}
