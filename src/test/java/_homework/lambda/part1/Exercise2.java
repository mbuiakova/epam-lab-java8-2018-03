package _homework.lambda.part1;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class Exercise2 {

    @FunctionalInterface
    private interface Multiplier<T> {

        T multiply(T value, int multiplier);

        default T twice(T t) {
            return multiply(t, 2);
        }
    }

    @Test
    void implementsIntegerMultiplierUsingAnonymousClass() {
        Exercise2.Multiplier<Integer> multiplier = new Multiplier<Integer>() {
            @Override
            public Integer multiply(Integer value, int multiplier) {
                return value*multiplier;
            }
        };

        testIntegerMultiplier(multiplier);
    }

    @Test
    void implementsMultiplierUsingStatementLambda() {
        Exercise2.Multiplier<Integer> multiplier = (value, multiplier1) -> value * multiplier1;

        testIntegerMultiplier(multiplier);
    }

    @Test
    void implementsIntegerMultiplierUsingExpressionLambda() {
        Exercise2.Multiplier<Integer> multiplier = (value, multiplier1) -> value * multiplier1;

        testIntegerMultiplier(multiplier);
    }

    private void testIntegerMultiplier(Exercise2.Multiplier<Integer> multiplier) {
        assertThat(multiplier.multiply(3, 2), is(6));
        assertThat(multiplier.multiply(Integer.MIN_VALUE, 0), is(0));
        assertThat(multiplier.multiply(7, -1), is(-7));
        assertThat(multiplier.twice(5), is(10));
        assertThat(multiplier.twice(0), is(0));
    }

    private static String multiplyString(String string, int number) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < number; ++i) {
            builder.append(string);
        }
        return builder.toString();
    }

    @Test
    void implementsStringMultiplierUsingClassMethodReference() {
        Exercise2.Multiplier<String> multiplier = Exercise2::multiplyString;

        assertThat(multiplier.multiply("a", 3), is("aaa"));
        assertThat(multiplier.multiply("qwerty", 0), is(emptyString()));
        assertThat(multiplier.twice("aA"), is("aAaA"));
        assertThat(multiplier.twice(""), is(emptyString()));
    }

    private final String delimiter = "-";

    private String stringSumWithDelimiter(String string, int number) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (int i = 0; i < number; ++i) {
            joiner.add(string);
        }
        String result = joiner.toString();
        return result.equals(delimiter) ? "" : result;
    }

    @Test
    void implementsStringMultiplierUsingObjectMethodReference() {
        Exercise2.Multiplier<String> multiplier = (string, number) -> stringSumWithDelimiter(string, number);

        assertThat(multiplier.multiply("a", 3), is("a-a-a"));
        assertThat(multiplier.multiply("qwerty", 0), is(emptyString()));
        assertThat(multiplier.twice("A"), is("A-A"));
        assertThat(multiplier.twice(""), is(emptyString()));
    }
}
