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
            return new SlugImplementation();
        }
    }
}
