package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Language specific symbol translations.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SymbolMapper {

    /**
     * Default language ({@value #DEFAULT}).
     */
    private static final Language DEFAULT = Language.ENGLISH;
    /**
     * Holds the mapping.
     */
    private static final Map<Language, Map<String, String>> MAPPING;

    static {
        final Map<Language, Map<String, String>> tmp = new HashMap<>();

        final Map<String, String> arabic = new HashMap<>();
        arabic.put("∆", "delta");
        arabic.put("∞", "la-nihaya");
        arabic.put("♥", "hob");
        arabic.put("&", "wa");
        arabic.put("|", "aw");
        arabic.put("<", "aqal-men");
        arabic.put(">", "akbar-men");
        arabic.put("∑", "majmou");
        arabic.put("¤", "omla");
        tmp.put(Language.ARABIC, Collections.unmodifiableMap(arabic));

        final Map<String, String> german = new HashMap<>();
        german.put("∆", "delta");
        german.put("∞", "unendlich");
        german.put("♥", "Liebe");
        german.put("&", "und");
        german.put("|", "oder");
        german.put("<", "kleiner als");
        german.put(">", "groesser als");
        german.put("∑", "Summe von");
        german.put("¤", "Waehrung");
        tmp.put(Language.GERMAN, Collections.unmodifiableMap(german));

        final Map<String, String> netherland = new HashMap<>();
        netherland.put("∆", "delta");
        netherland.put("∞", "oneindig");
        netherland.put("♥", "liefde");
        netherland.put("&", "en");
        netherland.put("|", "of");
        netherland.put("<", "kleiner dan");
        netherland.put(">", "groter dan");
        netherland.put("∑", "som");
        netherland.put("¤", "valuta");
        tmp.put(Language.NETHERLAND, Collections.unmodifiableMap(netherland));

        final Map<String, String> english = new HashMap<>();
        english.put("∆", "delta");
        english.put("∞", "infinity");
        english.put("♥", "love");
        english.put("&", "and");
        english.put("|", "or");
        english.put("<", "less than");
        english.put(">", "greater than");
        english.put("∑", "sum");
        english.put("¤", "currency");
        tmp.put(Language.ENGLISH, Collections.unmodifiableMap(english));

        final Map<String, String> spanish = new HashMap<>();
        spanish.put("∆", "delta");
        spanish.put("∞", "infinito");
        spanish.put("♥", "amor");
        spanish.put("&", "y");
        spanish.put("|", "u");
        spanish.put("<", "menos que");
        spanish.put(">", "mas que");
        spanish.put("∑", "suma de los");
        spanish.put("¤", "moneda");
        tmp.put(Language.SPANISH, Collections.unmodifiableMap(spanish));

        final Map<String, String> french = new HashMap<>();
        french.put("∆", "delta");
        french.put("∞", "infiniment");
        french.put("♥", "Amour");
        french.put("&", "et");
        french.put("|", "ou");
        french.put("<", "moins que");
        french.put(">", "superieure a");
        french.put("∑", "somme des");
        french.put("¤", "monnaie");
        tmp.put(Language.FRENCH, Collections.unmodifiableMap(french));

        final Map<String, String> portuguese = new HashMap<>();
        french.put("∆", "delta");
        french.put("∞", "infinito");
        french.put("♥", "amor");
        french.put("&", "e");
        french.put("|", "ou");
        french.put("<", "menor que");
        french.put(">", "maior que");
        french.put("∑", "soma");
        french.put("¤", "moeda");
        tmp.put(Language.PORTUGUESE, Collections.unmodifiableMap(portuguese));

        final Map<String, String> russian = new HashMap<>();
        russian.put("∆", "delta");
        russian.put("∞", "beskonechno");
        russian.put("♥", "lubov");
        russian.put("&", "i");
        russian.put("|", "ili");
        russian.put("<", "menshe");
        russian.put(">", "bolshe");
        russian.put("∑", "summa");
        russian.put("¤", "valjuta");
        tmp.put(Language.RUSSIAN, Collections.unmodifiableMap(russian));

        final Map<String, String> czech = new HashMap<>();
        czech.put("∆", "delta");
        czech.put("∞", "nekonecno");
        czech.put("♥", "laska");
        czech.put("&", "a");
        czech.put("|", "nebo");
        czech.put("<", "mene jako");
        czech.put(">", "vice jako");
        czech.put("∑", "soucet");
        czech.put("¤", "mena");
        tmp.put(Language.CZECH, Collections.unmodifiableMap(czech));

        final Map<String, String> swedish = new HashMap<>();
        swedish.put("∆", "delta");
        swedish.put("∞", "nekonecno");
        swedish.put("♥", "laska");
        swedish.put("&", "a");
        swedish.put("|", "alebo");
        swedish.put("<", "menej ako");
        swedish.put(">", "viac ako");
        swedish.put("∑", "sucet");
        swedish.put("¤", "mena");
        tmp.put(Language.SWEDISH, Collections.unmodifiableMap(swedish));

        final Map<String, String> vietnamese = new HashMap<>();
        vietnamese.put("∆", "delta");
        vietnamese.put("∞", "vo cuc");
        vietnamese.put("♥", "yeu");
        vietnamese.put("&", "va");
        vietnamese.put("|", "hoac");
        vietnamese.put("<", "nho hon");
        vietnamese.put(">", "lon hon");
        vietnamese.put("∑", "tong");
        vietnamese.put("¤", "tien te");
        tmp.put(Language.VIETNAMESE, Collections.unmodifiableMap(vietnamese));

        MAPPING = Collections.unmodifiableMap(tmp);
    }

    /**
     * Get the mapping for given language.
     *
     * <p>
     * If the given language has no mapping, then the mapping for {@link #DEFAULT}
     * will be returned.
     * </p>
     *
     * @param lang the get the mapping for
     * @return never {@code null}, immutable
     */
    Map<String, String> map(final Language lang) {
        if (MAPPING.containsKey(lang)) {
            return MAPPING.get(lang);
        }

        return MAPPING.get(DEFAULT);
    }

}
