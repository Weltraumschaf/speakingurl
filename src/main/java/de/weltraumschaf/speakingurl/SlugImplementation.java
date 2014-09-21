package de.weltraumschaf.speakingurl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Implementation of slug.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SlugImplementation implements Slug {

    private static final String URIC_NO_SLASH = ";?:@&=+$,";
    private static final String URIC = URIC_NO_SLASH + "/";
    private static final String MARK = ".!~*'()";

    private static final Pattern ALPHA_NUMERIC = Pattern.compile("/[a-zA-Z0-9]/");

    private final CharacterEscaper escaper = new CharacterEscaper();
    private final LanguageCharacterMapper languageMapper = new LanguageCharacterMapper();
    private final CharacterMappper characterMapper = new CharacterMappper();
    private final SymbolMapper symbolMapper = new SymbolMapper();
    private final Options options;

    public SlugImplementation(final Options options) {
        super();
        this.options = options;
    }

    @Override
    public String getSeparator() {
        return options.getSeparator();
    }

    @Override
    public Language getLang() {
        return options.getLang();
    }

    @Override
    public boolean isMaintainCase() {
        return options.isMaintainCase();
    }

    @Override
    public boolean isTitleCase() {
        return options.isTitleCase();
    }

    @Override
    public Set<String> getTitleCaseExclude() {
        return options.getTitleCaseExclude();
    }

    @Override
    public int getTruncate() {
        return options.getTruncate();
    }

    @Override
    public boolean isUric() {
        return options.isUric();
    }

    @Override
    public boolean isUricNoSlash() {
        return options.isUricNoSlash();
    }

    @Override
    public boolean isMark() {
        return options.isMark();
    }

    @Override
    public Map<String, String> getCustom() {
        return options.getCustom();
    }

    @Override
    public String get(final String input) {
        return get(input, options.getSeparator());
    }

    @Override
    public String get(final String rawInput, final String separator) {
        if (null == rawInput) {
            return "";
        }

        if (rawInput.trim().isEmpty()) {
            return "";
        }

        final Map<String, String> customReplacements = new HashMap<>(options.getCustom());

        if (options.isTitleCase()) {
            for (final String titleCaseExclude : options.getTitleCaseExclude()) {
                customReplacements.put(titleCaseExclude, titleCaseExclude);
            }
        }

        String allowedChars = separator;

        if (options.isUric()) {
            allowedChars += URIC;
        }

        if (options.isUricNoSlash()) {
            allowedChars += URIC_NO_SLASH;
        }

        if (options.isMark()) {
            allowedChars += MARK;
        }

        allowedChars = escaper.escape(allowedChars);
        String input = rawInput;
        String result = "";

        for (final String key : customReplacements.keySet()) {
            input = input.replaceAll(escaper.escape(key), customReplacements.get(key));
        }

        if (options.isTitleCase()) {
            // TODO Implement title case.
        }

        input = input.trim();

        final Map<String, String> langChars = languageMapper.map(options.getLang());
        final Map<String, String> symbol = symbolMapper.map(options.getLang());
        boolean lastCharWasSymbol = false;

        for (int i = 0, l = input.length(); i < l; i++) {
            String ch = input.substring(i, i + 1);

            if (langChars.containsKey(ch)) {
                if (lastCharWasSymbol && ALPHA_NUMERIC.matcher(langChars.get(ch)).matches()) {
                    ch = " " + langChars.get(ch);
                } else {
                    ch = langChars.get(ch);
                }

                lastCharWasSymbol = false;
            } else if (characterMapper.map().containsKey(ch)) {
                if (lastCharWasSymbol && ALPHA_NUMERIC.matcher(characterMapper.map().get(ch)).matches()) {
                    ch = " " + characterMapper.map().get(ch);
                } else {
                    ch = characterMapper.map().get(ch);
                }

                lastCharWasSymbol = false;
            } else if (
                symbol.containsKey(ch) && !(options.isUric() && URIC.indexOf(ch) != -1)
                && !(options.isUricNoSlash() && URIC_NO_SLASH.indexOf(ch) != -1)
                && !(options.isMark() && MARK.indexOf(ch) != -1)
            ) {
                // Process symbol chars.
                ch = lastCharWasSymbol || ALPHA_NUMERIC.matcher(result.substring(result.length() - 1)).matches()
                    ? separator + symbol.get(ch)
                    : symbol.get(ch);
                ch += ALPHA_NUMERIC.matcher(input.charAt(i + 1) + "").matches()
                    ? separator
                    : "";

                lastCharWasSymbol = true;
            } else {
                // Process latin chars.
                if (lastCharWasSymbol
                    && (
                        ALPHA_NUMERIC.matcher(ch).matches()
                        || ALPHA_NUMERIC.matcher(result.substring(result.length() - 1)).matches()
                    )
                ) {
                    ch = " " + ch;
                }

                lastCharWasSymbol = false;
            }

            // Add allowed chars.
            result += ch.replace("[^\\\\w\\\\s" + allowedChars + "_-]", separator);
        }

        // Eliminate duplicate separators,
        // add separator
        // and trim separators from start and end.
        result = result.replace("/\\s+/g", separator)
            .replace("/\\" + separator + "+/g", separator)
            .replace("/(^\\" + separator + "+|\\" + separator + "+$)/g", "");

        if (options.getTruncate() > 0 && result.length() > options.getTruncate()) {
            final boolean lucky = separator.equals(result.charAt(options.getTruncate()) + "");
            result = result.substring(0, options.getTruncate());

            if (!lucky) {
                result = result.substring(0, result.lastIndexOf(separator));
            }
        }

        if (!options.isMaintainCase() && !options.isTitleCase() && options.getTitleCaseExclude().isEmpty()) {
            result = result.toLowerCase();
        }

        return result;
    }

}
