package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Slugger's options.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class Options {

    private static final String DEFAULT_SEPARATOR = "-";
    private static final Language DEFAULT_LANG = Language.ENGLISH;
    private static final boolean DEFAULT_MAINTAIN_CASE = false;
    private static final boolean DEFAULT_TITLE_CASE = false;
    private static final Set<String> DEFAULT_TITLE_CASE_EXCLUDES = Collections.emptySet();
    private static final int DEFAULT_TRUNCATE = 0;
    private static final boolean DEFAULT_URIC = false;
    private static final boolean DEFAULT_URIC_NO_SLASH = false;
    private static final boolean DEFAULT_MARK = false;
    private static final Map<String, String> DEFAULT_CUSTOM = new HashMap<>();

    private String separator = DEFAULT_SEPARATOR;
    private Language lang = DEFAULT_LANG;
    private boolean maintainCase = DEFAULT_MAINTAIN_CASE;
    private boolean titleCase = DEFAULT_TITLE_CASE;
    private Set<String> titleCaseExclude = DEFAULT_TITLE_CASE_EXCLUDES;
    private int truncate = DEFAULT_TRUNCATE;
    private boolean uric = DEFAULT_URIC;
    private boolean uricNoSlash = DEFAULT_URIC_NO_SLASH;
    private boolean mark = DEFAULT_MARK;
    private Map<String, String> custom = DEFAULT_CUSTOM;

    String getSeparator() {
        return separator;
    }

    void setSeparator(final String separator) {
        this.separator = separator;
    }

    Language getLang() {
        return lang;
    }

    void setLang(final Language lang) {
        this.lang = lang;
    }

    boolean isMaintainCase() {
        return maintainCase;
    }

    void setMaintainCase(final boolean maintainCase) {
        this.maintainCase = maintainCase;
    }

    boolean isTitleCase() {
        return titleCase;
    }

    void setTitleCase(final boolean titleCase) {
        this.titleCase = titleCase;
    }

    Set<String> getTitleCaseExclude() {
        return titleCaseExclude;
    }

    void setTitleCaseExclude(final Set<String> titleCaseExclude) {
        this.titleCaseExclude = titleCaseExclude;
    }

    int getTruncate() {
        return truncate;
    }

    void setTruncate(final int truncate) {
        this.truncate = truncate;
    }

    boolean isUric() {
        return uric;
    }

    void setUric(final boolean uric) {
        this.uric = uric;
    }

    boolean isUricNoSlash() {
        return uricNoSlash;
    }

    void setUricNoSlash(final boolean uricNoSlash) {
        this.uricNoSlash = uricNoSlash;
    }

    boolean isMark() {
        return mark;
    }

    void setMark(final boolean mark) {
        this.mark = mark;
    }

    Map<String, String> getCustom() {
        return Collections.unmodifiableMap(custom);
    }

    void setCustom(final Map<String, String> custom) {
        this.custom = new HashMap<>(custom); // Defensive copy.
    }

    Options copy() {
        final Options copy = new Options();
        copy.separator = separator;
        copy.lang = lang;
        copy.maintainCase = maintainCase;
        copy.titleCase = titleCase;
        copy.titleCaseExclude = titleCaseExclude;
        copy.truncate = truncate;
        copy.uric = uric;
        copy.uricNoSlash = uricNoSlash;
        copy.mark = mark;
        copy.custom = custom;
        return copy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                separator,
                lang,
                maintainCase,
                titleCase,
                titleCaseExclude,
                truncate,
                uric,
                uricNoSlash,
                mark,
                custom
        );
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Options)) {
            return false;
        }

        final Options other = (Options) obj;
        return Objects.equals(separator, other.separator)
                && Objects.equals(lang, other.lang)
                && Objects.equals(maintainCase, other.maintainCase)
                && Objects.equals(titleCase, other.titleCase)
                && Objects.equals(titleCaseExclude, other.titleCaseExclude)
                && Objects.equals(truncate, other.truncate)
                && Objects.equals(uric, other.uric)
                && Objects.equals(uricNoSlash, other.uricNoSlash)
                && Objects.equals(mark, other.mark)
                && Objects.equals(custom, other.custom);
    }

    @Override
    public String toString() {
        return "void{"
                + "separator=" + separator
                + ", lang=" + lang
                + ", maintainCase=" + maintainCase
                + ", titleCase=" + titleCase
                + ", titleCaseExclude=" + titleCaseExclude
                + ", truncate=" + truncate
                + ", uric=" + uric
                + ", uricNoSlash=" + uricNoSlash
                + ", mark=" + mark
                + ", custom=" + custom + '}';
    }

}
