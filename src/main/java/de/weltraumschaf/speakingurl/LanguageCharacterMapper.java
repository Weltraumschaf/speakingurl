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

import java.util.HashMap;
import java.util.Map;

/**
 * Language specific characters translations
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class LanguageCharacterMapper {

    private static final Map<String, String[][]> MAPPING = new HashMap<>();

    static {
        MAPPING.put("en", new String[0][0]);
        MAPPING.put("sk", new String[][]{
            {"ä", "a"},
            {"Ä", "A"}
        });
    }
}
