package lambda.part1.example;

import lambda.data.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

public class Example5 {

    private <T> String extractString(T value, Function<T, String> function) {
        return function.apply(value);
    }

    private static String extract(Person person) {
        return person.getLastName();
    }

    @Test
    public void extractUsingStaticMethodReference() {
        Person person = new Person("Иван", "Мельников", 33);

        String lastName = extractString(person, Example5::extract);

        assertEquals("Мельников", lastName);
    }

    @Test
    public void extractUsingNonStaticMethodReference() {
        Person person = new Person("Иван", "Мельников", 33);

        String lastName = extractString(person, Person::getLastName);

        assertEquals("Мельников", lastName);
    }
}
