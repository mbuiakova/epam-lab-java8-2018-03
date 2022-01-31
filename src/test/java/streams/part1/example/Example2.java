package streams.part1.example;

import lambda.data.Employee;
import lambda.data.JobHistoryEntry;
import lambda.data.Person;
import lambda.part3.example.Example1;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @see <a href="https://youtu.be/kxgo7Y4cdA8">Через тернии к лямбдам, часть 1</a>
 * @see <a href="https://youtu.be/JRBWBJ6S4aU">Через тернии к лямбдам, часть 2</a>
 * @see <a href="https://youtu.be/O8oN4KSZEXE">Stream API, часть 1</a>
 * @see <a href="https://youtu.be/i0Jr2l3jrDA">Stream API, часть 2</a>
 */
@SuppressWarnings("ConstantConditions")
public class Example2 {

    @Test
    public void getIvansLastNames() {
        List<Employee> employees = Example1.getEmployees();

        String[] ivansLastNames = employees.stream()
                .map(Employee::getPerson)
                .map(Person::getLastName)
                .filter(lastName -> lastName.equals("Иван"))
                .toArray(value -> new String[value]);

        assertArrayEquals(new String[]{"Мельников", "Александров"}, ivansLastNames);
    }

    @Test
    public void checkAny25AgedIvanHasDevExperience() {
        List<Employee> employees = Example1.getEmployees();

//        boolean any25IvanHasDevExperience = employees.stream()
//                .filter(employee -> "Иван".equals(employee.getPerson().getFirstName()))
//                .filter(employee -> employee.getPerson().getAge() > 25)
//                .map(Employee::getJobHistory)
//                .flatMap(Collection::stream)
//                .map(JobHistoryEntry::getPosition)
////                                                     .filter("dev"::equals).findAny().isPresent();
//                .anyMatch("dev"::equals);
        boolean any25IvanHasDevExperience = employees.stream()
                .filter(e -> e.getPerson().getLastName().equals("Иван") && e.getPerson().getAge() > 25)
                .map(Employee::getJobHistory)
                .flatMap(list -> list.stream())
                .map(entty -> entty.getPosition())
                .anyMatch(entry -> entry.equals("dev"));

        boolean b = employees.stream()
                .filter(emp -> emp.getJobHistory().stream()
                        .map(e -> e.getPosition())
                        .anyMatch(e -> e.equals("dev")))
                .map(e -> e.getPerson())
                .filter(p -> p.getLastName().equals("Иван"))
                .anyMatch(p -> p.getAge() > 25);

        assertTrue(any25IvanHasDevExperience);
    }
}
