package de.weltraumschaf.speakingurl;

import java.util.HashMap;
import java.util.Map;

/**
 * Language specific symbol translations.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SymbolMapper {

    private static final Map<Language, String[][]> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Language.ARABIC, new String[][]{
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
        MAPPING.put(Language.GERMAN, new String[][]{
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
        MAPPING.put(Language.NETHERLAND, new String[][]{
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
        MAPPING.put(Language.ENGLISH, new String[][]{
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
        MAPPING.put(Language.SPAIN, new String[][]{
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
        MAPPING.put(Language.FRENCH, new String[][]{
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
        MAPPING.put(Language.PORTUGETH, new String[][]{
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
        MAPPING.put(Language.RUSSIAN, new String[][]{
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
        MAPPING.put(Language.CZECH, new String[][]{
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
        MAPPING.put(Language.SWEDISH, new String[][]{
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
        MAPPING.put(Language.VIETNAMESE, new String[][]{
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
