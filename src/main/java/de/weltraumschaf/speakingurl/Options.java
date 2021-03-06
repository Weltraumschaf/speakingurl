package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

    /**
     * Default separator.
     */
    private static final String DEFAULT_SEPARATOR = "-";
    /**
     * Default language.
     */
    private static final Language DEFAULT_LANG = Language.ENGLISH;
    /**
     * Default maintain case flag.
     */
    private static final boolean DEFAULT_MAINTAIN_CASE = false;
    /**
     * Default title case flag.
     */
    private static final boolean DEFAULT_TITLE_CASE = false;
    /**
     * Default title case excludes.
     */
    private static final Set<String> DEFAULT_TITLE_CASE_EXCLUDES = Collections.emptySet();
    /**
     * Default truncate length.
     */
    private static final int DEFAULT_TRUNCATE = 0;
    /**
     * Default URI characters flag.
     */
    private static final boolean DEFAULT_URIC = false;
    /**
     * Default URI characters without slash flag.
     */
    private static final boolean DEFAULT_URIC_WITHOUT_SLASH = false;
    /**
     * Default mark flag.
     */
    private static final boolean DEFAULT_MARK = false;
    /**
     * Default custom mapping.
     */
    private static final Map<String, String> DEFAULT_CUSTOM = new HashMap<>();

    /**
     * Validates input.
     */
    private final Validator validator = new Validator();
    /**
     * Used separator.
     */
    private String separator = DEFAULT_SEPARATOR;
    /**
     * Used language.
     */
    private Language language = DEFAULT_LANG;
    /**
     * Used maintain case flag.
     */
    private boolean maintainCase = DEFAULT_MAINTAIN_CASE;
    /**
     * Used title case flag.
     */
    private boolean titleCase = DEFAULT_TITLE_CASE;
    /**
     * Used title case excludes.
     */
    private Set<String> titleCaseExclude = DEFAULT_TITLE_CASE_EXCLUDES;
    /**
     * Used truncate length.
     */
    private int truncate = DEFAULT_TRUNCATE;
    /**
     * Used URI characters flag.
     */
    private boolean uric = DEFAULT_URIC;
    /**
     * Used URI characters without slash flag.
     */
    private boolean uricWithoutSlash = DEFAULT_URIC_WITHOUT_SLASH;
    /**
     * Used mark flag.
     */
    private boolean mark = DEFAULT_MARK;
    /**
     * Used custom mapping.
     */
    private Map<String, String> custom = DEFAULT_CUSTOM;

    /**
     * The separator.
     *
     * @return never {@code null} or empty
     */
    String separator() {
        return separator;
    }

    /**
     * Set the separator.
     *
     * @param withSeparator must not be {@code null} or empty
     */
    void separator(final String withSeparator) {
        this.separator = validator.notEmpty(withSeparator, "withSeparator");
    }

    /**
     * The language.
     *
     * @return never {@code null}
     */
    Language language() {
        return language;
    }

    /**
     * Set the language.
     *
     * @param withLanguage must not be {@code null}
     */
    void language(final Language withLanguage) {
        this.language = validator.notNull(withLanguage, "withLanguage");
    }

    /**
     * The maintain case flag.
     *
     * @return whether it is on or off
     */
    boolean maintainCase() {
        return maintainCase;
    }

    /**
     * Set maintain case flag.
     *
     * @param withMaintainCase maintainCase on or off
     */
    void maintainCase(final boolean withMaintainCase) {
        this.maintainCase = withMaintainCase;
    }

    /**
     * The title case flag.
     *
     * @return whether it is on or off
     */
    boolean titleCase() {
        return titleCase;
    }

    /**
     * Set title case flag.
     *
     * @param withTitleCase on or off
     */
    void titleCase(final boolean withTitleCase) {
        this.titleCase = withTitleCase;
    }

    /**
     * The words excluded from title case.
     *
     * @return never {@code null}, immutable
     */
    Set<String> titleCaseExclude() {
        return Collections.unmodifiableSet(titleCaseExclude);
    }

    /**
     * Set words excluded from title case.
     *
     * @param withTitleCaseExclude must not be {@code null}, defensive copied
     */
    void titleCaseExclude(final Set<String> withTitleCaseExclude) {
        this.titleCaseExclude = validator.notNull(new HashSet<>(withTitleCaseExclude), "withTitleCaseExclude");
    }

    /**
     * The truncate length.
     *
     * @return not negative
     */
    int truncate() {
        return truncate;
    }

    /**
     * Set the truncate length.
     *
     * @param withTruncate truncate must not be negative
     */
    void truncate(final int withTruncate) {
        this.truncate = validator.notNegative(withTruncate, "withTruncate");
    }

    /**
     * The URI characters flag.
     *
     * @return whether it is on or off
     */
    boolean uric() {
        return uric;
    }

    /**
     * Set URI characters flag.
     *
     * @param withUric uric on or off
     */
    void uric(final boolean withUric) {
        this.uric = withUric;
    }

    /**
     * The URI characters without slash flag.
     *
     * @return whether it is on or off
     */
    boolean uricWithoutSlash() {
        return uricWithoutSlash;
    }

    /**
     * Set URI characters without slash flag.
     *
     * @param withUricAndSlash on or off
     */
    void uricWithoutSlash(final boolean withUricAndSlash) {
        this.uricWithoutSlash = withUricAndSlash;
    }

    /**
     * The mark characters flag.
     *
     * @return whether it is on or off
     */
    boolean mark() {
        return mark;
    }

    /**
     * The mark characters flag.
     *
     * @param withMark on or off
     */
    void mark(final boolean withMark) {
        this.mark = withMark;
    }

    /**
     * The custom mapping.
     *
     * @return never {@code null}, immutable
     */
    Map<String, String> custom() {
        return Collections.unmodifiableMap(custom);
    }

    /**
     * Set custom mapping.
     *
     * @param withCustom must not be {@code null}, defensive copied
     */
    void custom(final Map<String, String> withCustom) {
        this.custom = new HashMap<>(validator.notNull(withCustom, "withCustom"));
    }

    /**
     * Makes copy of the object.
     *
     * @return never {@code null}, always new instance
     */
    Options copy() {
        final Options copy = new Options();
        copy.separator = separator;
        copy.language = language;
        copy.maintainCase = maintainCase;
        copy.titleCase = titleCase;
        copy.titleCaseExclude = titleCaseExclude;
        copy.truncate = truncate;
        copy.uric = uric;
        copy.uricWithoutSlash = uricWithoutSlash;
        copy.mark = mark;
        copy.custom = custom;
        return copy;
    }

    /**
     * Resets the object to the "factory settings".
     */
    void resetToDefaults() {
        separator = DEFAULT_SEPARATOR;
        language = DEFAULT_LANG;
        maintainCase = DEFAULT_MAINTAIN_CASE;
        titleCase = DEFAULT_TITLE_CASE;
        titleCaseExclude = DEFAULT_TITLE_CASE_EXCLUDES;
        truncate = DEFAULT_TRUNCATE;
        uric = DEFAULT_URIC;
        uricWithoutSlash = DEFAULT_URIC_WITHOUT_SLASH;
        mark = DEFAULT_MARK;
        custom = DEFAULT_CUSTOM;
    }

    @Override
    public int hashCode() {
        return Objects.hash(separator,
                language,
                maintainCase,
                titleCase,
                titleCaseExclude,
                truncate,
                uric,
                uricWithoutSlash,
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
                && Objects.equals(language, other.language)
                && Objects.equals(maintainCase, other.maintainCase)
                && Objects.equals(titleCase, other.titleCase)
                && Objects.equals(titleCaseExclude, other.titleCaseExclude)
                && Objects.equals(truncate, other.truncate)
                && Objects.equals(uric, other.uric)
                && Objects.equals(uricWithoutSlash, other.uricWithoutSlash)
                && Objects.equals(mark, other.mark)
                && Objects.equals(custom, other.custom);
    }

    @Override
    public String toString() {
        return "void{"
                + "separator=" + separator
                + ", lang=" + language
                + ", maintainCase=" + maintainCase
                + ", titleCase=" + titleCase
                + ", titleCaseExclude=" + titleCaseExclude
                + ", truncate=" + truncate
                + ", uric=" + uric
                + ", uricNoSlash=" + uricWithoutSlash
                + ", mark=" + mark
                + ", custom=" + custom + '}';
    }

}
