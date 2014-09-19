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
 * Implementations of this interface can create a slug from any string.
 * <p>
 * A slug is a so called 'static' or 'Clean URL' or 'Pretty  URL' or 
 * 'nice-looking URL' or 'Speaking  URL' or 'user-friendly URL' or 
 * 'SEO-friendly URL' from a  string. This module aims to transliterate the
 * input string.
 * </p>
 * <p>
 * The implementation is thread safe because the state of the slug is not 
 * stored in fields. Except of the options, but they are immutable. That's
 * the reason why you must use {@link Slug.Builder a builder} to configure 
 * and create a slug.
 * </p>
 * 
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public interface Slug {

    /**
     * Generates a slug from given input string.
     * 
     * @param input must not be {@code null}
     * @return never {@code null}, maybe empty
     */
    String get(String input);
    
    /**
     * Builder to configure and create the {@link  Slug slugger}.
     */
    public final class Builder {
        private static final String DEFAULT_SEPARATOR = "-";
        private static final Language DEFAULT_LANG = Language.ENGLISH;
        private static final boolean DEFAULT_MAINTAIN_CASE = false;
        private static final boolean DEFAULT_TITLE_CASE = false;
        private static final String[] DEFAULT_TITLE_CASE_EXCLUDES = new String[0];
        private static final int DEFAULT_TRUNCATE = 0;
        private static final boolean DEFAULT_URIC = false;
        private static final boolean DEFAULT_URIC_NO_SLASH = false;
        private static final boolean DEFAULT_MARK = false;
        private static final String[][] DEFAULT_CUSTOM = new String[0][0];
        
        private String separator = DEFAULT_SEPARATOR;
        private Language lang = DEFAULT_LANG;
        private boolean maintainCase = DEFAULT_MAINTAIN_CASE;
        private boolean titleCase = DEFAULT_TITLE_CASE;
        private String[] titleCaseExclude = DEFAULT_TITLE_CASE_EXCLUDES;
        private int truncate = DEFAULT_TRUNCATE;
        private boolean uric = DEFAULT_URIC;
        private boolean uricNoSlash = DEFAULT_URIC_NO_SLASH;
        private boolean mark = DEFAULT_MARK;
        private String[][] custom = DEFAULT_CUSTOM;
        
        private final Validator validator = new Validator();
        
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
            final SlugImplementation slugger = new SlugImplementation();
            
            slugger.separator = separator;
            slugger.lang = lang;
            slugger.maintainCase = maintainCase;
            slugger.titleCase = titleCase;
            slugger.titleCaseExclude = titleCaseExclude;
            slugger.truncate = truncate;
            slugger.uric = uric;
            slugger.uricNoSlash = uricNoSlash;
            slugger.mark = mark;
            slugger.custom = custom;
            
            return slugger;
        }

        /**
         * Character that replace the whitespaces (default is {@value #DEFAULT_SEPARATOR}).
         * 
         * @param separator must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder separator(final String separator) {
            this.separator = validator.notNull(separator, "separator");
            return this;
        }

        /**
         * Language for symbol translation (Default is {@link #DEFAULT_LANG}).
         * <p>
         * {@link Language#NONE} for don't convert symbols.
         * </p>
         * 
         * @param lang must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder lang(final Language lang) {
            this.lang = validator.notNull(lang, "lang");
            return this;
        }
        
        /**
         * How to deal with case of characters (default is {@value #DEFAULT_MAINTAIN_CASE}).
         * 
         * @param maintainCase if {@code true} maintain case, if {@code false}
         *                     convert all chars to lower case
         * @return self validated object for method chaining
         */
        public Builder maintainCase(final boolean maintainCase) {
            this.maintainCase = maintainCase;
            return this;
        }

        /**
         * Whether to convert input string to title-case (default is {@value #DEFAULT_TITLE_CASE}).
         * 
         * @param titleCase {@code true} to convert, else {@code false}
         * @return self validated object for method chaining
         */
        public Builder titleCase(final boolean titleCase) {
            this.titleCase = titleCase;
            return this;
        }

        /**
         * Words to exclude from title case (default is {@value #DEFAULT_TITLE_CASE_EXCLUDES}).
         * <p>
         * This option has only effect if {@link Builder#titleCase(boolean) titleCase}
         * is set {@code true}.
         * </p>
         * 
         * @param titleCaseExclude list of words to exclude, must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder titleCaseExclude(final String[] titleCaseExclude) {
            this.titleCaseExclude = validator.notNull(titleCaseExclude, "titleCaseExclude");
            return this;
        }

        /**
         * Max length to truncate without breaking words (default is {@value #DEFAULT_TRUNCATE}).
         * <p>
         * {@code 0} does not truncate.
         * </p>
         * 
         * @param truncate must not be negative
         * @return self validated object for method chaining
         */
        public Builder truncate(final int truncate) {
            this.truncate = validator.notNegative(truncate, "truncate");
            return this;
        }

        /**
         * Whether to allow additional characters (default {@value #DEFAULT_URIC}).
         * <p>
         * If {@code true} special characters ({@value SlugImplementation#URIC}).
         * </p>
         * 
         * @param uric if {@code true} they will not be converted
         * @return self validated object for method chaining
         */
        public Builder uric(final boolean uric) {
            this.uric = uric;
            return this;
        }

        /**
         * Whether to allow additional characters (default {@value #DEFAULT_URIC_NO_SLASH}).
         * <p>
         * If {@code true} special characters ({@value SlugImplementation#URIC_NO_SLASH}).
         * </p>
         * 
         * @param uric if {@code true} they will not be converted
         * @return self validated object for method chaining
         */
        public Builder uricNoSlash(final boolean uricNoSlash) {
            this.uricNoSlash = uricNoSlash;
            return this;
        }

        /**
         * Whether to allow additional characters (default {@value #DEFAULT_MARK}).
         * <p>
         * If {@code true} special characters ({@value SlugImplementation#MARK}).
         * </p>
         * 
         * @param uric if {@code true} they will not be converted
         * @return self validated object for method chaining
         */
        public Builder mark(final boolean mark) {
            this.mark = mark;
            return this;
        }

        /**
         * Custom map for translation (default is {@value #DEFAULT_CUSTOM}.
         * <p>
         * This overwrites all!
         * </p>
         * <p>
         * Example:
         * </p>
         * <pre>
         * {@code 
         *      { '&': '#', '*': ' star ' }
         * }
         * </pre>
         * 
         * FIXME Fix example.
         * 
         * @param custom must not be {@code null}
         * @return self validated object for method chaining
         */
        public Builder custom(final String[][] custom) {
            this.custom = validator.notNull(custom, "custom");
            return this;
        }
        
    }
}
