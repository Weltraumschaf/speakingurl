package de.weltraumschaf.speakingurl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementations of this interface can create a slug from any string.
 * <p>
 * A slug is a so called 'static' or 'Clean URL' or 'Pretty URL' or 'nice-looking URL' or 'Speaking URL' or
 * 'user-friendly URL' or 'SEO-friendly URL' from a string. This module aims to transliterate the input string.
 * </p>
 * <p>
 * The implementation is thread safe because the state of the slug is not stored in fields. Except of the options, but
 * they are immutable. That's the reason why you must use {@link Slug.Builder a builder} to configure and create a slug.
 * </p>
 *
 * @since 1.0.0
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Slug {

    /**
     * Generates a slug from given input string.
     *
     * @param input may be {@code null} or empty
     * @return never {@code null}, may be empty
     */
    String get(String input);

    /**
     * Generates a slug from given input string.
     *
     * @param input may be {@code null} or empty
     * @param separator must not be {@code null}
     * @return never {@code null}, may be empty
     */
    String get(String input, String separator);

    String getSeparator();

    Language getLang();

    boolean isMaintainCase();

    boolean isTitleCase();

    Set<String> getTitleCaseExclude();

    int getTruncate();

    boolean isUric();

    boolean isUricNoSlash();

    boolean isMark();

    Map<String, String> getCustom();

    /**
     * Builder to configure and create the {@link  Slug slugger}.
     *
     * TODO Remove validations because done in Options.
     * TODO Change method names according to Options.
     *
     * @since 1.0.0
     */
    public final class Builder {

        private final Validator validator = new Validator();
        private final Options options = new Options();

        /**
         * Use {@link #newBuiler() factory method} instead.
         */
        private Builder() {
            super();
        }

        /**
         * Creates new builder.
         *
         * @return never {@code null}, always new instance
         */
        public static Builder newBuiler() {
            return new Builder();
        }

        /**
         * Creates the {@link Slug slugger}.
         *
         * @return never {@code null}, always new instance
         */
        public Slug create() {
            return new SlugImplementation(options.copy());// Make copy to be threadsafe.
        }

        /**
         * Character that replace the whitespaces (default is {@value OptionsOptions#DEFAULT_SEPARATOR}).
         *
         * @param separator must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder separator(final String separator) {
            options.separator(validator.notNull(separator, "separator"));
            return this;
        }

        /**
         * Language for symbol translation (Default is {@link Options#DEFAULT_LANG}).
         * <p>
         * {@link Language#NONE} for don't convert symbols.
         * </p>
         *
         * @param lang must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder lang(final Language lang) {
            options.language(validator.notNull(lang, "lang"));
            return this;
        }

        /**
         * How to deal with case of characters (default is {@value Options#DEFAULT_MAINTAIN_CASE}).
         *
         * @param maintainCase if {@code true} maintain case, if {@code false} convert all chars to lower case
         * @return self validated object for method chaining
         */
        public Builder maintainCase(final boolean maintainCase) {
            options.maintainCase(maintainCase);
            return this;
        }

        /**
         * Whether to convert input string to title-case (default is {@value Options#DEFAULT_TITLE_CASE}).
         *
         * @param titleCase {@code true} to convert, else {@code false}
         * @return self validated object for method chaining
         */
        public Builder titleCase(final boolean titleCase) {
            options.titleCase(titleCase);
            return this;
        }

        /**
         * Words to exclude from title case (default is {@value Options#DEFAULT_TITLE_CASE_EXCLUDES}).
         * <p>
         * This option has only effect if {@link Builder#titleCase(boolean) titleCase} is set {@code true}.
         * </p>
         *
         * @param titleCaseExclude list of words to exclude, must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder titleCaseExclude(final String... titleCaseExclude) {
            validator.notNull(titleCaseExclude, "titleCaseExclude");
            titleCaseExclude(new HashSet<>(Arrays.asList(titleCaseExclude)));
            return this;
        }

        /**
         * Words to exclude from title case (default is {@value Options#DEFAULT_TITLE_CASE_EXCLUDES}).
         * <p>
         * This option has only effect if {@link Builder#titleCase(boolean) titleCase} is set {@code true}.
         * </p>
         *
         * @param titleCaseExclude list of words to exclude, must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder titleCaseExclude(final Set<String> titleCaseExclude) {
            options.titleCaseExclude(Collections.unmodifiableSet(
                    validator.notNull(titleCaseExclude, "titleCaseExclude")));
            return this;
        }

        /**
         * Max length to truncate without breaking words (default is {@value Options#DEFAULT_TRUNCATE}).
         * <p>
         * {@code 0} does not truncate.
         * </p>
         *
         * @param truncate must not be negative
         * @return self validated object for method chaining
         */
        public Builder truncate(final int truncate) {
            options.truncate(validator.notNegative(truncate, "truncate"));
            return this;
        }

        /**
         * Whether to allow additional characters (default {@value Options#DEFAULT_URIC}).
         * <p>
         * If {@code true} special characters ({@value SlugImplementation#URIC}).
         * </p>
         *
         * @param uric if {@code true} they will not be converted
         * @return self validated object for method chaining
         */
        public Builder uric(final boolean uric) {
            options.uric(uric);
            return this;
        }

        /**
         * Whether to allow additional characters (default {@value Options#DEFAULT_URIC_NO_SLASH}).
         * <p>
         * If {@code true} special characters ({@value SlugImplementation#URIC_NO_SLASH}).
         * </p>
         *
         * @param uricNoSlash if {@code true} they will not be converted
         * @return self validated object for method chaining
         */
        public Builder uricNoSlash(final boolean uricNoSlash) {
            options.uricWithoutSlash(uricNoSlash);
            return this;
        }

        /**
         * Whether to allow additional characters (default {@value Options#DEFAULT_MARK}).
         * <p>
         * If {@code true} special characters ({@value SlugImplementation#MARK}).
         * </p>
         *
         * @param mark if {@code true} they will not be converted
         * @return self validated object for method chaining
         */
        public Builder mark(final boolean mark) {
            options.mark(mark);
            return this;
        }

        /**
         * Custom map for translation (default is {@value Options#DEFAULT_CUSTOM}.
         * <p>
         * This overwrites all!
         * </p>
         * <p>
         * Example:
         * </p>
         * <pre>
         * {@code
         * final Slug.Builder builder = Slug.Builder.newBuilder();
         * ...
         * final Map<String, String> custom = new HashMap<>();
         * custom.put("&", "#");
         * custom.put("*", "star");
         * ...
         * builder.custom(custom);
         * }
         * </pre>
         *
         * @param custom must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder custom(final Map<String, String> custom) {
            options.custom(validator.notNull(custom, "custom"));
            return this;
        }
    }
}
