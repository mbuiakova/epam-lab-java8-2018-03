package lambda.part3.exercise;

import lambda.data.Employee;
import lambda.part3.example.Example1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Exercise1 {

    @Test
    public void mapEmployeesToLengthOfTheirFullNames() {
        List<Employee> employees = Example1.getEmployees();
        List<Integer> lengths = new ArrayList<>();

        // TODO функция извлечения информации о человеке из объекта сотрудника personExtractor: Employee -> Person
        // TODO функция извлечения полного имени из информации о человеке fullNameExtractor: Person -> String
        // TODO функция извлечения длины из строки stringLengthExtractor: String -> Integer
        // TODO функция извлечения длины полного имени из сотрудника fullNameLengthExtractor: Employee -> Integer
        // TODO преобразование списка employees в lengths используя fullNameLengthExtractor

        assertEquals(Arrays.asList(14, 19, 14, 15, 14, 16), lengths);
    }
}
