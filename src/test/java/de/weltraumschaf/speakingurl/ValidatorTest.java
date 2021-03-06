package de.weltraumschaf.speakingurl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Tests for {@link Validator}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class ValidatorTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final Validator sut = new Validator();

    @Test
    public void notNull_exceptionIfNameNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("The name must not be null!");

        sut.notNull(new Object(), null);
    }

    @Test
    public void notNull_exceptionIfNameEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The name must not be empty!");

        sut.notNull(new Object(), "");
    }

    @Test
    public void notNull_exceptionIfNameOnlySpaces() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The name must not be empty!");

        sut.notNull(new Object(), "    ");
    }

    @Test
    public void notNull_exceptionIfValidatedIsNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Parameter 'foobar' must not be null!");

        sut.notNull(null, "foobar");
    }

    @Test
    public void notNull_returnsValidated() {
        final Object validated = new Object();

        assertThat(sut.notNull(validated, "foobar"), is(sameInstance(validated)));
    }

    @Test
    public void notEmpty_exceptionIfNameNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("The name must not be null!");

        sut.notEmpty("foo", null);
    }

    @Test
    public void notEmpty_exceptionIfNameEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The name must not be empty!");

        sut.notEmpty("foo", "");
    }

    @Test
    public void notEmpty_exceptionIfNameOnlySpaces() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The name must not be empty!");

        sut.notEmpty("foo", "    ");
    }

    @Test
    public void notEmpty_exceptionIfValidatedIsNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Parameter 'foobar' must not be null!");

        sut.notEmpty(null, "foobar");
    }

    @Test
    public void notEmpty_exceptionIfValidatedIsEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Parameter 'foobar' must not be empty!");

        sut.notEmpty("", "foobar");
    }

    @Test
    public void notEmpty_exceptionIfValidatedOnlySpaces() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Parameter 'foobar' must not be empty!");

        sut.notEmpty("   ", "foobar");
    }

    @Test
    public void notEmpty_returnsValidated() {
        assertThat(sut.notNull("foo", "foobar"), is(sameInstance("foo")));
    }

    @Test
    public void notNegative_exceptionIfNameNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("The name must not be null!");

        sut.notNegative(0, null);
    }

    @Test
    public void notNegative_exceptionIfNameEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The name must not be empty!");

        sut.notNegative(0, "");
    }

    @Test
    public void notNegative_exceptionIfNameOnlySpaces() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The name must not be empty!");

        sut.notNegative(0, "    ");
    }

    @Test
    public void notNegative_exceptionIfValidatedIsMinusOne() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Parameter 'foobar' must not be negative (was '-1')!");

        sut.notNegative(-1, "foobar");
    }

    @Test
    public void notNegative_exceptionIfValidatedIsMinusTwo() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Parameter 'foobar' must not be negative (was '-2')!");

        sut.notNegative(-2, "foobar");
    }

    @Test
    public void notNegative_exceptionIfValidatedIsMinusThree() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Parameter 'foobar' must not be negative (was '-3')!");

        sut.notNegative(-3, "foobar");
    }

    @Test
    public void notNegative_exceptionIfValidatedIsMinus23() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Parameter 'foobar' must not be negative (was '-23')!");

        sut.notNegative(-23, "foobar");
    }
    @Test
    public void notNegative_returnsValidated() {
        assertThat(sut.notNegative(0, "foobar"), is(equalTo(0)));
        assertThat(sut.notNegative(1, "foobar"), is(equalTo(1)));
        assertThat(sut.notNegative(2, "foobar"), is(equalTo(2)));
        assertThat(sut.notNegative(23, "foobar"), is(equalTo(23)));
        assertThat(sut.notNegative(42, "foobar"), is(equalTo(42)));
    }
}
