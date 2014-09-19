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
 * Language specific symbol translations.
 *
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SymbolMapper {

    private static final Map<String, String[][]> MAPPING = new HashMap<>();

    static {
        MAPPING.put("ar", new String[][]{
            {"∆", "delta"},
            {"∞", "la-nihaya"},
            {"♥", "hob"},
            {"&", "wa"},
            {"|", "aw"},
            {"<", "aqal-men"},
            {">", "akbar-men"},
            {"∑", "majmou"},
            {"¤", "omla"}
        });
        MAPPING.put("de", new String[][]{
            {"∆", "delta"},
            {"∞", "unendlich"},
            {"♥", "Liebe"},
            {"&", "und"},
            {"|", "oder"},
            {"<", "kleiner als"},
            {">", "groesser als"},
            {"∑", "Summe von"},
            {"¤", "Waehrung"}
        });
        MAPPING.put("nl", new String[][]{
            {"∆", "delta"},
            {"∞", "oneindig"},
            {"♥", "liefde"},
            {"&", "en"},
            {"|", "of"},
            {"<", "kleiner dan"},
            {">", "groter dan"},
            {"∑", "som"},
            {"¤", "valuta"}
        });
        MAPPING.put("en", new String[][]{
            {"∆", "delta"},
            {"∞", "infinity"},
            {"♥", "love"},
            {"&", "and"},
            {"|", "or"},
            {"<", "less than"},
            {">", "greater than"},
            {"∑", "sum"},
            {"¤", "currency"}
        });
        MAPPING.put("es", new String[][]{
            {"∆", "delta"},
            {"∞", "infinito"},
            {"♥", "amor"},
            {"&", "y"},
            {"|", "u"},
            {"<", "menos que"},
            {">", "mas que"},
            {"∑", "suma de los"},
            {"¤", "moneda"}
        });
        MAPPING.put("fr", new String[][]{
            {"∆", "delta"},
            {"∞", "infiniment"},
            {"♥", "Amour"},
            {"&", "et"},
            {"|", "ou"},
            {"<", "moins que"},
            {">", "superieure a"},
            {"∑", "somme des"},
            {"¤", "monnaie"}
        });
        MAPPING.put("pt", new String[][]{
            {"∆", "delta"},
            {"∞", "infinito"},
            {"♥", "amor"},
            {"&", "e"},
            {"|", "ou"},
            {"<", "menor que"},
            {">", "maior que"},
            {"∑", "soma"},
            {"¤", "moeda"}
        });
        MAPPING.put("ru", new String[][]{
            {"∆", "delta"},
            {"∞", "beskonechno"},
            {"♥", "lubov"},
            {"&", "i"},
            {"|", "ili"},
            {"<", "menshe"},
            {">", "bolshe"},
            {"∑", "summa"},
            {"¤", "valjuta"}
        });
        MAPPING.put("cz", new String[][]{
            {"∆", "delta"},
            {"∞", "nekonecno"},
            {"♥", "laska"},
            {"&", "a"},
            {"|", "nebo"},
            {"<", "mene jako"},
            {">", "vice jako"},
            {"∑", "soucet"},
            {"¤", "mena"}
        });
        MAPPING.put("sk", new String[][]{
            {"∆", "delta"},
            {"∞", "nekonecno"},
            {"♥", "laska"},
            {"&", "a"},
            {"|", "alebo"},
            {"<", "menej ako"},
            {">", "viac ako"},
            {"∑", "sucet"},
            {"¤", "mena"}
        });
        MAPPING.put("vn", new String[][]{
            {"∆", "delta"},
            {"∞", "vo cuc"},
            {"♥", "yeu"},
            {"&", "va"},
            {"|", "hoac"},
            {"<", "nho hon"},
            {">", "lon hon"},
            {"∑", "tong"},
            {"¤", "tien te"}
        });
    }
}
