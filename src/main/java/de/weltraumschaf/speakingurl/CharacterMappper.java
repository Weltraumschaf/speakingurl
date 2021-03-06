package de.weltraumschaf.speakingurl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Maps characters not wanted in a speaking URL.
 *
 * FIXME Fix Arabic diactrics.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class CharacterMappper {

    /**
     * Holds the mapping.
     */
    private static final Map<String, String> MAPPING;

    static {
        final Map<String, String> tmp = new HashMap<>();

        // Latin.
        tmp.put("À", "A");
        tmp.put("Á", "A");
        tmp.put("Â", "A");
        tmp.put("Ã", "A");
        tmp.put("Ä", "Ae");
        tmp.put("Å", "A");
        tmp.put("Æ", "AE");
        tmp.put("Ç", "C");
        tmp.put("È", "E");
        tmp.put("É", "E");
        tmp.put("Ê", "E");
        tmp.put("Ë", "E");
        tmp.put("Ì", "I");
        tmp.put("Í", "I");
        tmp.put("Î", "I");
        tmp.put("Ï", "I");
        tmp.put("Ð", "D");
        tmp.put("Ñ", "N");
        tmp.put("Ò", "O");
        tmp.put("Ó", "O");
        tmp.put("Ô", "O");
        tmp.put("Õ", "O");
        tmp.put("Ö", "Oe");
        tmp.put("Ő", "O");
        tmp.put("Ø", "O");
        tmp.put("Ù", "U");
        tmp.put("Ú", "U");
        tmp.put("Û", "U");
        tmp.put("Ü", "Ue");
        tmp.put("Ű", "U");
        tmp.put("Ý", "Y");
        tmp.put("Þ", "TH");
        tmp.put("ß", "ss");
        tmp.put("à", "a");
        tmp.put("á", "a");
        tmp.put("â", "a");
        tmp.put("ã", "a");
        tmp.put("ä", "ae");
        tmp.put("å", "a");
        tmp.put("æ", "ae");
        tmp.put("ç", "c");
        tmp.put("è", "e");
        tmp.put("é", "e");
        tmp.put("ê", "e");
        tmp.put("ë", "e");
        tmp.put("ì", "i");
        tmp.put("í", "i");
        tmp.put("î", "i");
        tmp.put("ï", "i");
        tmp.put("ð", "d");
        tmp.put("ñ", "n");
        tmp.put("ò", "o");
        tmp.put("ó", "o");
        tmp.put("ô", "o");
        tmp.put("õ", "o");
        tmp.put("ö", "oe");
        tmp.put("ő", "o");
        tmp.put("ø", "o");
        tmp.put("ù", "u");
        tmp.put("ú", "u");
        tmp.put("û", "u");
        tmp.put("ü", "ue");
        tmp.put("ű", "u");
        tmp.put("ý", "y");
        tmp.put("þ", "th");
        tmp.put("ÿ", "y");
        tmp.put("ẞ", "SS");
        // Greek.
        tmp.put("α", "a");
        tmp.put("β", "v");
        tmp.put("γ", "g");
        tmp.put("δ", "d");
        tmp.put("ε", "e");
        tmp.put("ζ", "z");
        tmp.put("η", "i");
        tmp.put("θ", "th");
        tmp.put("ι", "i");
        tmp.put("κ", "k");
        tmp.put("λ", "l");
        tmp.put("μ", "m");
        tmp.put("ν", "n");
        tmp.put("ξ", "ks");
        tmp.put("ο", "o");
        tmp.put("π", "p");
        tmp.put("ρ", "r");
        tmp.put("σ", "s");
        tmp.put("τ", "t");
        tmp.put("υ", "y");
        tmp.put("φ", "f");
        tmp.put("χ", "x");
        tmp.put("ψ", "ps");
        tmp.put("ω", "o");
        tmp.put("ά", "a");
        tmp.put("έ", "e");
        tmp.put("ί", "i");
        tmp.put("ό", "o");
        tmp.put("ύ", "y");
        tmp.put("ή", "i");
        tmp.put("ώ", "o");
        tmp.put("ς", "s");
        tmp.put("ϊ", "i");
        tmp.put("ΰ", "y");
        tmp.put("ϋ", "y");
        tmp.put("ΐ", "i");
        tmp.put("Α", "A");
        tmp.put("Β", "B");
        tmp.put("Γ", "G");
        tmp.put("Δ", "D");
        tmp.put("Ε", "E");
        tmp.put("Ζ", "Z");
        tmp.put("Η", "I");
        tmp.put("Θ", "TH");
        tmp.put("Ι", "I");
        tmp.put("Κ", "K");
        tmp.put("Λ", "L");
        tmp.put("Μ", "M");
        tmp.put("Ν", "N");
        tmp.put("Ξ", "KS");
        tmp.put("Ο", "O");
        tmp.put("Π", "P");
        tmp.put("Ρ", "R");
        tmp.put("Σ", "S");
        tmp.put("Τ", "T");
        tmp.put("Υ", "Y");
        tmp.put("Φ", "F");
        tmp.put("Χ", "X");
        tmp.put("Ψ", "PS");
        tmp.put("Ω", "W");
        tmp.put("Ά", "A");
        tmp.put("Έ", "E");
        tmp.put("Ί", "I");
        tmp.put("Ό", "O");
        tmp.put("Ύ", "Y");
        tmp.put("Ή", "I");
        tmp.put("Ώ", "O");
        tmp.put("Ϊ", "I");
        tmp.put("Ϋ", "Y");
        // Turkish.
        tmp.put("ş", "s");
        tmp.put("Ş", "S");
        tmp.put("ı", "i");
        tmp.put("İ", "I");
        // MAPPING.put("ç", "c"); // duplicate
        // MAPPING.put("Ç", "C"); // duplicate
        // MAPPING.put("ü", "ue"); // duplicate
        // MAPPING.put("Ü", "Ue"); // duplicate
        // MAPPING.put("ö", "oe"); // duplicate
        // MAPPING.put("Ö", "Oe"); // duplicate
        tmp.put("ğ", "g");
        tmp.put("Ğ", "G");
        // Macedonian.
        tmp.put("Ќ", "Kj");
        tmp.put("ќ", "kj");
        tmp.put("Љ", "Lj");
        tmp.put("љ", "lj");
        tmp.put("Њ", "Nj");
        tmp.put("њ", "nj");
        tmp.put("Тс", "Ts");
        tmp.put("тс", "ts");
        // Russian.
        tmp.put("а", "a");
        tmp.put("б", "b");
        tmp.put("в", "v");
        tmp.put("г", "g");
        tmp.put("д", "d");
        tmp.put("е", "e");
        tmp.put("ё", "yo");
        tmp.put("ж", "zh");
        tmp.put("з", "z");
        tmp.put("и", "i");
        tmp.put("й", "j");
        tmp.put("к", "k");
        tmp.put("л", "l");
        tmp.put("м", "m");
        tmp.put("н", "n");
        tmp.put("о", "o");
        tmp.put("п", "p");
        tmp.put("р", "r");
        tmp.put("с", "s");
        tmp.put("т", "t");
        tmp.put("у", "u");
        tmp.put("ф", "f");
        tmp.put("х", "h");
        tmp.put("ц", "c");
        tmp.put("ч", "ch");
        tmp.put("ш", "sh");
        tmp.put("щ", "sh");
        tmp.put("ъ", "");
        tmp.put("ы", "y");
        tmp.put("ь", "");
        tmp.put("э", "e");
        tmp.put("ю", "yu");
        tmp.put("я", "ya");
        tmp.put("А", "A");
        tmp.put("Б", "B");
        tmp.put("В", "V");
        tmp.put("Г", "G");
        tmp.put("Д", "D");
        tmp.put("Е", "E");
        tmp.put("Ё", "Yo");
        tmp.put("Ж", "Zh");
        tmp.put("З", "Z");
        tmp.put("И", "I");
        tmp.put("Й", "J");
        tmp.put("К", "K");
        tmp.put("Л", "L");
        tmp.put("М", "M");
        tmp.put("Н", "N");
        tmp.put("О", "O");
        tmp.put("П", "P");
        tmp.put("Р", "R");
        tmp.put("С", "S");
        tmp.put("Т", "T");
        tmp.put("У", "U");
        tmp.put("Ф", "F");
        tmp.put("Х", "H");
        tmp.put("Ц", "C");
        tmp.put("Ч", "Ch");
        tmp.put("Ш", "Sh");
        tmp.put("Щ", "Sh");
        tmp.put("Ъ", "");
        tmp.put("Ы", "Y");
        tmp.put("Ь", "");
        tmp.put("Э", "E");
        tmp.put("Ю", "Yu");
        tmp.put("Я", "Ya");
        // Ukranian.
        tmp.put("Є", "Ye");
        tmp.put("І", "I");
        tmp.put("Ї", "Yi");
        tmp.put("Ґ", "G");
        tmp.put("є", "ye");
        tmp.put("і", "i");
        tmp.put("ї", "yi");
        tmp.put("ґ", "g");
        // Czech.
        tmp.put("č", "c");
        tmp.put("ď", "d");
        tmp.put("ě", "e");
        tmp.put("ň", "n");
        tmp.put("ř", "r");
        tmp.put("š", "s");
        tmp.put("ť", "t");
        tmp.put("ů", "u");
        tmp.put("ž", "z");
        tmp.put("Č", "C");
        tmp.put("Ď", "D");
        tmp.put("Ě", "E");
        tmp.put("Ň", "N");
        tmp.put("Ř", "R");
        tmp.put("Š", "S");
        tmp.put("Ť", "T");
        tmp.put("Ů", "U");
        tmp.put("Ž", "Z");
        // Slovak.
        tmp.put("ľ", "l");
        tmp.put("ĺ", "l");
        tmp.put("ŕ", "r");
        tmp.put("Ľ", "L");
        tmp.put("Ĺ", "L");
        tmp.put("Ŕ", "R");
        // Polish.
        tmp.put("ą", "a");
        tmp.put("ć", "c");
        tmp.put("ę", "e");
        tmp.put("ł", "l");
        tmp.put("ń", "n");
        // MAPPING.put("ó", "o"); // duplicate
        tmp.put("ś", "s");
        tmp.put("ź", "z");
        tmp.put("ż", "z");
        tmp.put("Ą", "A");
        tmp.put("Ć", "C");
        tmp.put("Ę", "E");
        tmp.put("Ł", "L");
        tmp.put("Ń", "N");
        tmp.put("Ś", "S");
        tmp.put("Ź", "Z");
        tmp.put("Ż", "Z");
        // Latvian.
        tmp.put("ā", "a");
        // MAPPING.put("č", "c"); // duplicate
        tmp.put("ē", "e");
        tmp.put("ģ", "g");
        tmp.put("ī", "i");
        tmp.put("ķ", "k");
        tmp.put("ļ", "l");
        tmp.put("ņ", "n");
        // MAPPING.put("š", "s"); // duplicate
        tmp.put("ū", "u");
        // MAPPING.put("ž", "z"); // duplicate
        tmp.put("Ā", "A");
        // MAPPING.put("Č", "C"); // duplicate
        tmp.put("Ē", "E");
        tmp.put("Ģ", "G");
        tmp.put("Ī", "I");
        tmp.put("Ķ", "k");
        tmp.put("Ļ", "L");
        tmp.put("Ņ", "N");
        // MAPPING.put("Š", "S"); // duplicate
        tmp.put("Ū", "U");
        // MAPPING.put("Ž", "Z"); // duplicate
        // Arabic.
        tmp.put("ا", "a");
        tmp.put("أ", "a");
        tmp.put("إ", "i");
        tmp.put("آ", "aa");
        tmp.put("ؤ", "u");
        tmp.put("ئ", "e");
        tmp.put("ء", "a");
        tmp.put("ب", "b");
        tmp.put("ت", "t");
        tmp.put("ث", "th");
        tmp.put("ج", "j");
        tmp.put("ح", "h");
        tmp.put("خ", "kh");
        tmp.put("د", "d");
        tmp.put("ذ", "th");
        tmp.put("ر", "r");
        tmp.put("ز", "z");
        tmp.put("س", "s");
        tmp.put("ش", "sh");
        tmp.put("ص", "s");
        tmp.put("ض", "dh");
        tmp.put("ط", "t");
        tmp.put("ظ", "z");
        tmp.put("ع", "a");
        tmp.put("غ", "gh");
        tmp.put("ف", "f");
        tmp.put("ق", "q");
        tmp.put("ك", "k");
        tmp.put("ل", "l");
        tmp.put("م", "m");
        tmp.put("ن", "n");
        tmp.put("ه", "h");
        tmp.put("و", "w");
        tmp.put("ي", "y");
        tmp.put("ى", "a");
        tmp.put("ة", "h");
        tmp.put("ﻻ", "la");
        tmp.put("ﻷ", "laa");
        tmp.put("ﻹ", "lai");
        tmp.put("ﻵ", "laa");
        // Arabic diactrics.
        tmp.put("َ", "a");
        tmp.put("ً", "an");
        tmp.put("ِ", "e");
        tmp.put("ٍ", "en");
        tmp.put("ُ", "u");
        tmp.put("ٌ", "on");
        tmp.put("ْ", "");
        // Arabic numbers.
        tmp.put("٠", "0");
        tmp.put("١", "1");
        tmp.put("٢", "2");
        tmp.put("٣", "3");
        tmp.put("٤", "4");
        tmp.put("٥", "5");
        tmp.put("٦", "6");
        tmp.put("٧", "7");
        tmp.put("٨", "8");
        tmp.put("٩", "9");
        // Symbols.
        tmp.put("“", "\"");
        tmp.put("”", "\"");
        tmp.put("‘", "'");
        tmp.put("’", "'");
        tmp.put("∂", "d");
        tmp.put("ƒ", "f");
        tmp.put("™", "(TM)");
        tmp.put("©", "(C)");
        tmp.put("œ", "oe");
        tmp.put("Œ", "OE");
        tmp.put("®", "(R)");
        tmp.put("†", "+");
        tmp.put("℠", "(SM)");
        tmp.put("…", "...");
        tmp.put("˚", "o");
        tmp.put("º", "o");
        tmp.put("ª", "a");
        tmp.put("•", "*");
        // Currencies.
        tmp.put("$", "USD");
        tmp.put("€", "EUR");
        tmp.put("₢", "BRN");
        tmp.put("₣", "FRF");
        tmp.put("£", "GBP");
        tmp.put("₤", "ITL");
        tmp.put("₦", "NGN");
        tmp.put("₧", "ESP");
        tmp.put("₩", "KRW");
        tmp.put("₪", "ILS");
        tmp.put("₫", "VND");
        tmp.put("₭", "LAK");
        tmp.put("₮", "MNT");
        tmp.put("₯", "GRD");
        tmp.put("₱", "ARS");
        tmp.put("₲", "PYG");
        tmp.put("₳", "ARA");
        tmp.put("₴", "UAH");
        tmp.put("₵", "GHS");
        tmp.put("¢", "cent");
        tmp.put("¥", "CNY");
        tmp.put("元", "CNY");
        tmp.put("円", "YEN");
        tmp.put("﷼", "IRR");
        tmp.put("₠", "EWE");
        tmp.put("฿", "THB");
        tmp.put("₨", "INR");
        tmp.put("₹", "INR");
        tmp.put("₰", "PF");
        // Vietnamese.
        tmp.put("đ", "d");
        tmp.put("Đ", "D");
        tmp.put("ẹ", "e");
        tmp.put("Ẹ", "E");
        tmp.put("ẽ", "e");
        tmp.put("Ẽ", "E");
        tmp.put("ế", "e");
        tmp.put("Ế", "E");
        tmp.put("ề", "e");
        tmp.put("Ề", "E");
        tmp.put("ệ", "e");
        tmp.put("Ệ", "E");
        tmp.put("ễ", "e");
        tmp.put("Ễ", "E");
        tmp.put("ọ", "o");
        tmp.put("Ọ", "o");
        tmp.put("ố", "o");
        tmp.put("Ố", "O");
        tmp.put("ồ", "o");
        tmp.put("Ồ", "O");
        tmp.put("ộ", "o");
        tmp.put("Ộ", "O");
        tmp.put("ỗ", "o");
        tmp.put("Ỗ", "O");
        tmp.put("ơ", "o");
        tmp.put("Ơ", "O");
        tmp.put("ớ", "o");
        tmp.put("Ớ", "O");
        tmp.put("ờ", "o");
        tmp.put("Ờ", "O");
        tmp.put("ợ", "o");
        tmp.put("Ợ", "O");
        tmp.put("ỡ", "o");
        tmp.put("Ỡ", "O");
        tmp.put("ị", "i");
        tmp.put("Ị", "I");
        tmp.put("ĩ", "i");
        tmp.put("Ĩ", "I");
        tmp.put("ụ", "u");
        tmp.put("Ụ", "U");
        tmp.put("ũ", "u");
        tmp.put("Ũ", "U");
        tmp.put("ư", "u");
        tmp.put("Ư", "U");
        tmp.put("ứ", "u");
        tmp.put("Ứ", "U");
        tmp.put("ừ", "u");
        tmp.put("Ừ", "U");
        tmp.put("ự", "u");
        tmp.put("Ự", "U");
        tmp.put("ữ", "u");
        tmp.put("Ữ", "U");
        tmp.put("ỳ", "y");
        tmp.put("Ỳ", "Y");
        tmp.put("ỵ", "y");
        tmp.put("Ỵ", "Y");
        tmp.put("ỹ", "y");
        tmp.put("Ỹ", "Y");
        tmp.put("ạ", "a");
        tmp.put("Ạ", "A");
        tmp.put("ấ", "a");
        tmp.put("Ấ", "A");
        tmp.put("ầ", "a");
        tmp.put("Ầ", "A");
        tmp.put("ậ", "a");
        tmp.put("Ậ", "A");
        tmp.put("ẫ", "a");
        tmp.put("Ẫ", "A");
        tmp.put("ă", "a");
        tmp.put("Ă", "A");
        tmp.put("ắ", "a");
        tmp.put("Ắ", "A");
        tmp.put("ằ", "a");
        tmp.put("Ằ", "A");
        tmp.put("ặ", "a");
        tmp.put("Ặ", "A");
        tmp.put("ẵ", "a");
        tmp.put("Ẵ", "A");

        MAPPING = Collections.unmodifiableMap(tmp);
    }

    /**
     * Validates input.
     */
    private final Validator validator = new Validator();

    /**
     * Whether the maps know the character to mapCharacter.
     *
     * @param ch must not be {@code null}
     * @return {@code true} if known, else {@code false}
     */
    boolean knowsCharacter(final String ch) {
        return MAPPING.containsKey(validator.notNull(ch, "ch"));
    }

    /**
     * Maps a given character.
     * <p>
     * Throws an {@link IllegalArgumentException} if the given character
     * {@link #knowsCharacter(java.lang.String) is not knwon}.
     * </p>
     *
     * @param ch must not be {@code null}
     * @return never {@code null}
     */
    String mapCharacter(final String ch) {
        if (ch == null || ch.isEmpty()) {
            return "";
        }

        if (knowsCharacter(ch)) {
            return MAPPING.get(ch);
        }

        throw new IllegalArgumentException(String.format("Can't map character '%s'!", ch));
    }
}
