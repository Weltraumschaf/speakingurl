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
     * @since 1.0.0
     */
    public final class Builder {

        /**
         * Data holder.
         */
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
         * Character that replace the whitespaces.
         *
         * @param separator must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder separator(final String separator) {
            options.separator(separator);
            return this;
        }

        /**
         * Language for symbol translation.
         * <p>
         * {@link Language#NONE} for don't convert symbols.
         * </p>
         *
         * @param lang must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder lang(final Language lang) {
            options.language(lang);
            return this;
        }

        /**
         * How to deal with case of characters.
         *
         * @param maintainCase if {@code true} maintain case, if {@code false} convert all chars to lower case
         * @return self validated object for method chaining
         */
        public Builder maintainCase(final boolean maintainCase) {
            options.maintainCase(maintainCase);
            return this;
        }

        /**
         * Whether to convert input string to title-case.
         *
         * @param titleCase {@code true} to convert, else {@code false}
         * @return self validated object for method chaining
         */
        public Builder titleCase(final boolean titleCase) {
            options.titleCase(titleCase);
            return this;
        }

        /**
         * Words to exclude from title case.
         * <p>
         * This option has only effect if {@link Builder#titleCase(boolean) titleCase} is set {@code true}.
         * </p>
         *
         * @param titleCaseExclude list of words to exclude, must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder titleCaseExclude(final String... titleCaseExclude) {
            titleCaseExclude(new HashSet<>(Arrays.asList(titleCaseExclude)));
            return this;
        }

        /**
         * Words to exclude from title case.
         * <p>
         * This option has only effect if {@link Builder#titleCase(boolean) titleCase} is set {@code true}.
         * </p>
         *
         * @param titleCaseExclude list of words to exclude, must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder titleCaseExclude(final Set<String> titleCaseExclude) {
            options.titleCaseExclude(Collections.unmodifiableSet(titleCaseExclude));
            return this;
        }

        /**
         * Max length to truncate without breaking words.
         * <p>
         * {@code 0} does not truncate.
         * </p>
         *
         * @param truncate must not be negative
         * @return self validated object for method chaining
         */
        public Builder truncate(final int truncate) {
            options.truncate(truncate);
            return this;
        }

        /**
         * Whether to allow additional characters.
         * <p>
         * If {@code true} special characters will not be replaced.
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
         * Whether to allow additional characters.
         * <p>
         * If {@code true} special characters will not be replaced.
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
         * Whether to allow additional characters.
         * <p>
         * If {@code true} special characters will not be replaced.
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
         * Custom map for translation.
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
            options.custom(custom);
            return this;
        }
    }
}
