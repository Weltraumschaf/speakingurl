package de.weltraumschaf.speakingurl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of slug.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class SlugImplementation implements Slug {

    /**
     * URI characters without slash.
     */
    private static final String URIC_WITHOUT_SLASH = ";?:@&=+$,";
    /**
     * URI characters with slash.
     */
    private static final String URIC_SLASH = "/";
    /**
     * Punctuation characters.
     */
    private static final String MARK = ".!~*'()";
    /**
     * Matches alpha numeric characters.
     */
    private static final Pattern ALPHA_NUMERIC = Pattern.compile("[a-zA-Z0-9]+");

    /**
     * Validates input.
     */
    private final Validator validator = new Validator();
    /**
     * Maps general characters.
     */
    private final CharacterMappper characterMapper = new CharacterMappper();
    /**
     * Holds the slugger options.
     */
    private final Options options;
    /**
     * Maps language dependent characters.
     */
    private final Map<String, String> langChars;
    /**
     * Maps language dependent symbols.
     */
    private final Map<String, String> symbols;

    /**
     * Creates slugger with default options.
     */
    SlugImplementation() {
        this(new Options());
    }

    /**
     * Dedicated constructor.
     *
     * @param options must not be {@code null}
     */
    SlugImplementation(final Options options) {
        super();
        this.options = validator.notNull(options, "options");
        this.langChars = new LanguageCharacterMapper().map(options.language());
        this.symbols = new SymbolMapper().map(options.language());
    }

    @Override
    public String getSeparator() {
        return options.separator();
    }

    @Override
    public Language getLang() {
        return options.language();
    }

    @Override
    public boolean isMaintainCase() {
        return options.maintainCase();
    }

    @Override
    public boolean isTitleCase() {
        return options.titleCase();
    }

    @Override
    public Set<String> getTitleCaseExclude() {
        return options.titleCaseExclude();
    }

    @Override
    public int getTruncate() {
        return options.truncate();
    }

    @Override
    public boolean isUric() {
        return options.uric();
    }

    @Override
    public boolean isUricNoSlash() {
        return options.uricWithoutSlash();
    }

    @Override
    public boolean isMark() {
        return options.mark();
    }

    @Override
    public Map<String, String> getCustom() {
        return options.custom();
    }

    @Override
    public String get(final String input) {
        return get(input, options.separator());
    }

    @Override
    public String get(final String rawInput, final String separator) {
        if (null == rawInput) {
            return "";
        }

        if (rawInput.trim().isEmpty()) {
            return "";
        }

        final Map<String, String> customReplacements = new HashMap<>(options.custom());

        if (options.titleCase()) {
            for (final String titleCaseExclude : options.titleCaseExclude()) {
                customReplacements.put(titleCaseExclude, titleCaseExclude);
            }
        }

        final String allowedChars = generateAllowedCharatcers(separator);
        String input = rawInput;
        String result = "";

        for (final String key : customReplacements.keySet()) {
            input = input.replaceAll(Pattern.quote(key), customReplacements.get(key));
        }

        if (options.titleCase()) {
            input = transformCase(input, customReplacements);
        }

        input = input.trim();
        boolean lastCharWasSymbol = false;

        for (int i = 0, l = input.length(); i < l; i++) {
            String ch = currentCharacter(input, i);

            if (langChars.containsKey(ch)) {
                ch = replaceLanguageCharacters(lastCharWasSymbol, ch);

                lastCharWasSymbol = false;
            } else if (characterMapper.map().containsKey(ch)) {
                ch = replaceCharacters(lastCharWasSymbol, ch);

                lastCharWasSymbol = false;
            } else if (symbols.containsKey(ch)
                    && !(options.uric() && URIC_SLASH.contains(ch))
                    && !(options.uricWithoutSlash() && URIC_WITHOUT_SLASH.contains(ch))
                    && !(options.mark() && MARK.contains(ch))) {
                ch = replaceSymbols(ch, lastCharWasSymbol, result, separator, input, i);

                lastCharWasSymbol = true;
            } else {
                // Process latin chars.
                if (lastCharWasSymbol
                        && (ALPHA_NUMERIC.matcher(ch).matches()
                        || ALPHA_NUMERIC.matcher(result.substring(result.length() - 1)).matches())) {
                    ch = " " + ch;
                }

                lastCharWasSymbol = false;
            }

            result += replaceNotAllowedCharacters(ch, allowedChars, separator);
        }

        result = cleanupReplacements(result, separator);

        if (options.truncate() > 0 && result.length() > options.truncate()) {
            final boolean lucky = separator.equals(result.charAt(options.truncate()) + "");
            result = result.substring(0, options.truncate());

            if (!lucky) {
                result = result.substring(0, result.lastIndexOf(separator));
            }
        }

        if (!options.maintainCase() && !options.titleCase() && options.titleCaseExclude().isEmpty()) {
            result = result.toLowerCase();
        }

        return result;
    }

    String generateAllowedCharatcers(final String separator) {
        final StringBuilder allowedChars = new StringBuilder();
        allowedChars.append(validator.notEmpty(separator, "separator"));

        if (options.uricWithoutSlash() || options.uric()) {
            allowedChars.append(URIC_WITHOUT_SLASH);
        }

        if (options.uric()) {
            allowedChars.append(URIC_SLASH);
        }

        if (options.mark()) {
            allowedChars.append(MARK);
        }

        return Pattern.quote(allowedChars.toString());
    }

    String currentCharacter(final String input, final int index) {
        validator.notNull(input, "input");
        validator.notNegative(index, "index");

        return input.substring(index, index + 1);
    }

    String replaceSymbols(
            final String ch,
            final boolean lastCharWasSymbol,
            final String result,
            final String separator,
            final String input,
            final int index) {
        if (ch == null) {
            return "";
        }

        if (ch.isEmpty()) {
            return "";
        }

        String buffer = "";
        final int prevPos = result.length() - 1;

        if (prevPos >= 0) {
            buffer = lastCharWasSymbol || ALPHA_NUMERIC.matcher(result.substring(prevPos)).matches()
                ? separator + symbols.get(ch)
                : symbols.get(ch);
        }

        final int nextPos = index + 1;

        if (nextPos < input.length()) {
            final char next = input.charAt(nextPos);

            if (ALPHA_NUMERIC.matcher(String.valueOf(next)).matches()) {
                buffer += separator;
            }
        }

        return buffer;
    }

    String replaceCharacters(final boolean lastCharWasSymbol, final String ch) {
        if (ch == null) {
            return "";
        }

        if (ch.isEmpty()) {
            return "";
        }

        if (lastCharWasSymbol && ALPHA_NUMERIC.matcher(characterMapper.map().get(ch)).matches()) {
            return " " + characterMapper.map().get(ch);
        }

        return characterMapper.map().get(ch);
    }

    String replaceLanguageCharacters(final boolean lastCharWasSymbol, final String ch) {
        if (ch == null) {
            return "";
        }

        if (ch.isEmpty()) {
            return "";
        }

        final String replacement = langChars.containsKey(ch)
                ? langChars.get(ch)
                : ch;

        if (lastCharWasSymbol && ALPHA_NUMERIC.matcher(replacement).matches()) {
            return " " + replacement;
        }

        return replacement;
    }

    String replaceNotAllowedCharacters(final String ch, final String allowedChars, final String separator) {
        return ch.replaceAll("[^\\w\\s" + allowedChars + "_\\-]", separator);
    }

    String cleanupReplacements(final String result, final String separator) {
        String tmp = replaceWhitespaces(result, separator);
        tmp = replaceDuplicateSeparators(tmp, separator);
        return replaceLeadingAndTrailingSeparator(tmp, separator);
    }

    String replaceWhitespaces(final String result, final String separator) {
        return result.replaceAll("\\s+", separator);
    }

    String replaceDuplicateSeparators(final String result, final String separator) {
        return result.replaceAll(Pattern.quote(separator) + "+", separator);
    }

    String replaceLeadingAndTrailingSeparator(final String result, final String separator) {
        return result.replaceAll("(^" + Pattern.quote(separator) + "+"
                + "|" + Pattern.quote(separator) + "+$)", "");
    }

    String transformCase(final String input, final Map<String, String> customReplacements) {
        final Pattern pattern = Pattern.compile("(\\w\\S*)");
        final Matcher matcher = pattern.matcher(input);
        final StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            final String match = ucfirst(matcher.group());

            if (customReplacements.containsKey(match.toLowerCase())) {
                matcher.appendReplacement(buffer, match);
            } else {
                matcher.appendReplacement(buffer, match.toLowerCase());
            }
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    /**
     * Makes the first character upper case.
     *
     * @param input may be {@code null} or empty
     * @return never {@code null}, may be empty
     */
    String ucfirst(final String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        char[] chars = new char[1];
        input.getChars(0, 1, chars, 0);

        if (Character.isUpperCase(chars[0])) {
            return input;
        } else {
            StringBuilder buffer = new StringBuilder(input.length());
            buffer.append(Character.toUpperCase(chars[0]));
            buffer.append(input.toCharArray(), 1, input.length() - 1);
            return buffer.toString();
        }
    }
}
