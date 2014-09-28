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
    private static final Pattern ALPHA_NUMERIC = Pattern.compile("[a-zA-Z0-9]");
    /**
     * Matches each word for titles.
     */
    private static final Pattern TITLE = Pattern.compile("(\\w\\S*)");
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
        String input = rawInput.trim();

        for (final String key : customReplacements.keySet()) {
            final String pattern;

            if (key.length() > 1) {
                // Math to word boundary.
                pattern = "\\b" + Pattern.quote(key) + "\\b";
            } else {
                pattern = Pattern.quote(key);
            }

            input = input.replaceAll(pattern, customReplacements.get(key));
        }

        if (options.titleCase()) {
            input = transformCase(input, customReplacements);
        }

        String result = processByCharacters(input, separator, allowedChars);
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

    private String processByCharacters(final String input, final String separator, final String allowedChars) {
        String result = "";
        boolean lastCharWasSymbol = false;

        for (int i = 0, l = input.length(); i < l; i++) {
            String ch = currentCharacter(input, i);

            if (langChars().containsKey(ch)) {
                ch = replaceLanguageCharacters(lastCharWasSymbol, ch);

                lastCharWasSymbol = false;
            } else if (characterMapper.map().containsKey(ch)) {
                ch = replaceCharacters(lastCharWasSymbol, ch);

                lastCharWasSymbol = false;
            } else if (symbols().containsKey(ch)
                    && !(options.uric() && URIC_SLASH.contains(ch))
                    && !(options.uricWithoutSlash() && URIC_WITHOUT_SLASH.contains(ch))
                    && !(options.mark() && MARK.contains(ch))) {
                ch = replaceSymbols(ch, lastCharWasSymbol, result, separator, input, i);

                lastCharWasSymbol = true;
            } else {
                // Process latin chars.
                if (i > 0) {
                    final String lastResultChar = result.substring(result.length() - 1);

                    if (lastCharWasSymbol
                            && (ALPHA_NUMERIC.matcher(ch).matches()
                            || Pattern.compile("a-zA-Z0-9]").matcher(lastResultChar).matches())) {
                        ch = " " + ch;
                    }
                }

                lastCharWasSymbol = false;
            }

            result += replaceNotAllowedCharacters(ch, allowedChars, separator);
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
            final int currentPos) {
        if (ch == null) {
            return "";
        }

        if (ch.isEmpty()) {
            return "";
        }

        final StringBuilder buffer = new StringBuilder();

        if (null != result) {
            final int prevPos = result.length() - 1;

            if (prevPos >= 0) {
                final String lastChar = result.substring(prevPos);

                if (lastCharWasSymbol || ALPHA_NUMERIC.matcher(lastChar).matches()) {
                    buffer.append(separator);
                }
            }
        }

        buffer.append(symbols().get(ch));

        if (input != null) {
            final int nextPos = currentPos + 1;

            if (nextPos < input.length()) {
                final char nextChar = input.charAt(nextPos);

                if (ALPHA_NUMERIC.matcher(String.valueOf(nextChar)).matches()) {
                    buffer.append(separator);
                }
            }
        }

        return buffer.toString();
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

        final String replacement = langChars().containsKey(ch)
                ? langChars().get(ch)
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

    /**
     * Make each word which starts with alphanumeric character to upper case, except them in the replacement map.
     *
     * @param input may be {@code null} or empty
     * @param exclusiions map with key and value same
     * @return never {@code null}, may be empty
     */
    String transformCase(final String input, final Map<String, String> exclusiions) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        final Pattern pattern = TITLE;
        final Matcher matcher = pattern.matcher(input);
        final StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            final String match = ucfirst(matcher.group());

            if (null != exclusiions && exclusiions.containsKey(match.toLowerCase())) {
                matcher.appendReplacement(buffer, match.toLowerCase());
            } else {
                matcher.appendReplacement(buffer, match);
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

    /**
     * Maps language dependent characters.
     */
    private Map<String, String> langChars() {
        return new LanguageCharacterMapper().map(options.language());
    }

    /**
     * Maps language dependent symbols.
     */
    private Map<String, String> symbols() {
        return new SymbolMapper().map(options.language());
    }
}
