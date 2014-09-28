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
    public String separator() {
        return options.separator();
    }

    @Override
    public Language lang() {
        return options.language();
    }

    @Override
    public boolean maintainCase() {
        return options.maintainCase();
    }

    @Override
    public boolean titleCase() {
        return options.titleCase();
    }

    @Override
    public Set<String> titleCaseExclude() {
        return options.titleCaseExclude();
    }

    @Override
    public int truncate() {
        return options.truncate();
    }

    @Override
    public boolean uric() {
        return options.uric();
    }

    @Override
    public boolean uricNoSlash() {
        return options.uricWithoutSlash();
    }

    @Override
    public boolean mark() {
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

        for (final Map.Entry<String, String> entry : customReplacements.entrySet()) {
            final String pattern;

            if (entry.getKey().length() > 1) {
                // Math to word boundary.
                pattern = "\\b" + Pattern.quote(entry.getKey()) + "\\b";
            } else {
                pattern = Pattern.quote(entry.getKey());
            }

            input = input.replaceAll(pattern, entry.getValue());
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

    /**
     * Processes each character of the input string.
     *
     * @param input must not be {@cde null}
     * @param separator must not be {@code null} or empty
     * @param allowedChars must not be {@cde null}
     * @return never {@code null}
     */
    private String processByCharacters(final String input, final String separator, final String allowedChars) {
        final StringBuilder result = new StringBuilder();
        boolean lastCharWasSymbol = false;
        final int length = input.length();

        for (int i = 0; i < length; i++) {
            String ch = currentCharacter(input, i);

            if (new LanguageCharacterMapper().knowsCharacter(options.language(), ch)) {
                ch = replaceLanguageCharacters(lastCharWasSymbol, ch);

                lastCharWasSymbol = false;
            } else if (characterMapper.knowsCharacter(ch)) {
                ch = replaceCharacters(ch, lastCharWasSymbol);

                lastCharWasSymbol = false;
            } else if (new SymbolMapper().knowsSymbol(options.language(), ch)
                    && !(options.uric() && (URIC_WITHOUT_SLASH + URIC_SLASH).contains(ch))
                    && !(options.uricWithoutSlash() && URIC_WITHOUT_SLASH.contains(ch))
                    && !(options.mark() && MARK.contains(ch))) {
                ch = replaceSymbols(ch, lastCharWasSymbol, result.toString(), separator, input, i);

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

            result.append(replaceNotAllowedCharacters(ch, allowedChars, separator));
        }

        return result.toString();
    }

    /**
     * Generates a string containing all allowed characters.
     *
     * @param separator must not be {@code null} or empty
     * @return never {@code null}
     */
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

    /**
     * Returns character at a given position from a string.
     *
     * @param input string to get character from
     * @param position the obtained position
     * @return never {@code null}
     */
    String currentCharacter(final String input, final int position) {
        validator.notNull(input, "input");
        validator.notNegative(position, "index");

        return input.substring(position, position + 1);
    }

    /**
     * Replace symbol characters.
     *
     * @param ch the current character to replace
     * @param lastCharWasSymbol whether the last character was a symbol
     * @param result the current result
     * @param separator must not be {@code null} or empty
     * @param input the preprocessed input string
     * @param currentPos the current position in the input string
     * @return never {@code null}
     */
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

        buffer.append(new SymbolMapper().mapSymbol(options.language(), ch));

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

    /**
     * Replace characters.
     *
     * @param ch the current character to replace
     * @param lastCharWasSymbol whether the last character was a symbol
     * @return never {@code null}
     */
    String replaceCharacters(final String ch, final boolean lastCharWasSymbol) {
        if (ch == null) {
            return "";
        }

        if (ch.isEmpty()) {
            return "";
        }

        if (lastCharWasSymbol && ALPHA_NUMERIC.matcher(characterMapper.mapCharacter(ch)).matches()) {
            return " " + characterMapper.mapCharacter(ch);
        }

        return characterMapper.mapCharacter(ch);
    }

    /**
     * Replace language depending characters.
     *
     * @param lastCharWasSymbol whether the last character was a symbol
     * @param ch the current character to replace
     * @return never {@code null}
     */
    String replaceLanguageCharacters(final boolean lastCharWasSymbol, final String ch) {
        if (ch == null) {
            return "";
        }

        if (ch.isEmpty()) {
            return "";
        }

        final String replacement = new LanguageCharacterMapper().knowsCharacter(options.language(), ch)
                ? new LanguageCharacterMapper().mapCharacter(options.language(), ch)
                : ch;

        if (lastCharWasSymbol && ALPHA_NUMERIC.matcher(replacement).matches()) {
            return " " + replacement;
        }

        return replacement;
    }

    /**
     * Replace all not allowed characters.
     *
     * @param ch the current character to replace
     * @param allowedChars string containing all allowed characters
     * @param separator must not be {@code null} or empty
     * @return never {@code null}
     */
    String replaceNotAllowedCharacters(final String ch, final String allowedChars, final String separator) {
        return ch.replaceAll("[^\\w\\s" + allowedChars + "_\\-]", separator);
    }

    /**
     * Cleans the resulting slug from duplicate separators and such.
     *
     * @param input will be cleaned
     * @param separator must not be {@code null} or empty
     * @return never {@code null}
     */
    String cleanupReplacements(final String input, final String separator) {
        String tmp = replaceWhitespaces(input, separator);
        tmp = replaceDuplicateSeparators(tmp, separator);
        return replaceLeadingAndTrailingSeparator(tmp, separator);
    }

    /**
     * Replace all whitespaces with separators.
     *
     * @param input will be processed
     * @param separator must not be {@code null} or empty
     * @return never {@code null}
     */
    String replaceWhitespaces(final String input, final String separator) {
        return input.replaceAll("\\s+", separator);
    }

    /**
     * Removes duplicate separators.
     *
     * @param input will be processed
     * @param separator must not be {@code null} or empty
     * @return never {@code null}
     */
    String replaceDuplicateSeparators(final String input, final String separator) {
        return input.replaceAll(Pattern.quote(separator) + "+", separator);
    }

    /**
     * Removes leading and trailing separators.
     *
     * @param input will be processed
     * @param separator must not be {@code null} or empty
     * @return never {@code null}
     */
    String replaceLeadingAndTrailingSeparator(final String input, final String separator) {
        return input.replaceAll("(^" + Pattern.quote(separator) + "+"
                + "|" + Pattern.quote(separator) + "+$)", "");
    }

    /**
     * Make each word which starts with alphanumeric character to upper case,
     * except them in the replacement mapCharacter.
     *
     * @param input may be {@code null} or empty
     * @param exclusiions mapCharacter with key and value same
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

        final char[] chars = new char[1];
        input.getChars(0, 1, chars, 0);

        if (Character.isUpperCase(chars[0])) {
            return input;
        } else {
            final StringBuilder buffer = new StringBuilder(input.length());
            buffer.append(Character.toUpperCase(chars[0]));
            buffer.append(input.toCharArray(), 1, input.length() - 1);
            return buffer.toString();
        }
    }

}
