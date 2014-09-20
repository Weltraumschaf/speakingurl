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
     * @param validated asserted to be not {@code null}
     * @param name name of asserted parameter, must not be {@code null} or empty
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
    
    int notNegative(final int  validated, final String name) {
        validateName(name);
        
        if (validated < 0) {
            throw new IllegalArgumentException(
                String.format("Parameter '%s' must not be negative (was '%d')!", name, validated));
        }
        
        return validated;
    }
    
    private void validateName(final String name) {
        if (null == name) {
            throw new NullPointerException("The name must not be null!");
        }
        
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("The name must not be empty!");
        }
    }
}
