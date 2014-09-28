package de.weltraumschaf.speakingurl;

/**
 * Provides convenience methods for input validation.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
final class Validator {

    /**
     * Throws an {@link NullPointerException} if validated object is {@code null}.
     * <p>
     * Sets a proper exception messages which names the parameter which is
     * {@code null}. Instead of throwing the exception with empty message and
     * the developer must dig into the stacktrace (if available).
     * </p>
     *
     * @param <T> type of validated object
     * @param validated asserted to be not {@code null}
     * @param name of asserted parameter, must not be {@code null} or empty
     * @return validated object for method chaining
     */
    <T> T notNull(final T validated, final String name) {
        validateName(name);

        if (null == validated) {
            throw new NullPointerException(
                String.format("Parameter '%s' must not be null!", name));
        }

        return validated;
    }

    /**
     * Throws an {@link NullPointerException} if validated string is {@code null} or
     * an {@link IllegalArgumentException} if it is empty.
     *
     * @see #notNull(java.lang.Object, java.lang.String)
     * @param validated asserted to be not {@code null} or empty
     * @param name of asserted parameter, must not be {@code null} or empty
     * @return validated string for method chaining
     */
    String notEmpty(final String validated, final String name) {
        notNull(validated, name);

        if (validated.trim().isEmpty()) {
            throw new IllegalArgumentException(String.format("Parameter '%s' must not be empty!", name));
        }

        return validated;
    }

    /**
     * Validates that the given integer is not negative.
     * <p>
     * Throws an {@link IllegalArgumentException} if given number is less than zero.
     * </p>
     *
     * @param validated asserted to be not less than 0
     * @param name of asserted parameter, must not be {@code null} or empty
     * @return validated integer for method chaining
     */
    int notNegative(final int  validated, final String name) {
        validateName(name);

        if (validated < 0) {
            throw new IllegalArgumentException(
                String.format("Parameter '%s' must not be negative (was '%d')!", name, validated));
        }

        return validated;
    }

    /**
     * Validates that the given name is not {@code null} or empty.
     * <p>
     * Throws a {@link NullPointerException} if name is {@code null}
     * or an {@link IllegalArgumentException} if it is empty.
     * </p>
     *
     * @param name must not be {@code null} or empty
     */
    private void validateName(final String name) {
        if (null == name) {
            throw new NullPointerException("The name must not be null!");
        }

        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name must not be empty!");
        }
    }
}
