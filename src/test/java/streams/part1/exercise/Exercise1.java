package streams.part1.exercise;

import lambda.data.Employee;
import lambda.data.Person;
import lambda.part3.example.Example1;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings({"ConstantConditions", "unused"})
public class Exercise1 {

    @Test
    public void findPersonsEverWorkedInEpam() {
        List<Employee> employees = Example1.getEmployees();

        // TODO реализация, использовать Collectors.toList()
        List<Person> personsEverWorkedInEpam = employees.stream()
                .filter(employee -> employee.getJobHistory().stream()
                        .map(e -> e.getEmployer())
                        .anyMatch(e -> "EPAM".equals(e)))
                .map(employee -> employee.getPerson())
                .collect(Collectors.toList());

        List<Person> expected = Arrays.asList(
                employees.get(0).getPerson(),
                employees.get(1).getPerson(),
                employees.get(4).getPerson(),
                employees.get(5).getPerson());
        assertEquals(expected, personsEverWorkedInEpam);
    }

    @Test
    public void findPersonsBeganCareerInEpam() {
        List<Employee> employees = Example1.getEmployees();

        // TODO реализация, использовать Collectors.toList()
        List<Person> startedFromEpam = employees.stream()
                .filter(employee -> employee.getJobHistory().get(0).getEmployer().equals("EPAM"))
                .map(employee -> employee.getPerson())
                .collect(Collectors.toList());

        List<Person> expected = Arrays.asList(
                employees.get(0).getPerson(),
                employees.get(1).getPerson(),
                employees.get(4).getPerson());
        assertEquals(expected, startedFromEpam);
    }

    @Test
    public void findAllCompanies() {
        List<Employee> employees = Example1.getEmployees();

        // TODO реализация, использовать Collectors.toSet()
        Set<String> companies = employees.stream()
                .map(employee -> employee.getJobHistory())
                .flatMap(list -> list.stream())
                .map(entry -> entry.getEmployer())
                .collect(Collectors.toSet());

        Set<String> expected = new HashSet<>();
        expected.add("EPAM");
        expected.add("google");
        expected.add("yandex");
        expected.add("mail.ru");
        expected.add("T-Systems");
        assertEquals(expected, companies);
    }

    @Test
    public void findMinimalAgeOfEmployees() {
        List<Employee> employees = Example1.getEmployees();

        // TODO реализация
        Integer minimalAge = employees.stream()
                .mapToInt(employee -> employee.getPerson().getAge())
                .min().getAsInt();

        assertEquals(21, minimalAge.intValue());
    }
}