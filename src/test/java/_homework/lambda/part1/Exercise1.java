package _homework.lambda.part1;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import lambda.data.Person;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

public class Exercise1 {
    @Test
    void sortPersonsByAgeUsingArraysSortLocalComparator() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort
        Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        };
        Arrays.sort(persons, comparator);

        assertThat(persons, is(arrayContaining(
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        )));
    }

    @Test
    void sortPersonsByAgeUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        assertThat(persons, is(arrayContaining(
                new Person("Иван", "Мельников", 20),
                new Person("Николай", "Зимов", 30),
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45)
        )));
    }

    @Test
    void sortPersonsByLastNameThenFirstNameUsingArraysSortAnonymousComparator() {
        Person[] persons = getPersons();

        // TODO use Arrays.sort
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int a = o1.getLastName().compareTo(o2.getLastName());
                if(a == 0) {
                    return o1.getFirstName().compareTo(o2.getFirstName());}
                return a;
            }
        });

        assertThat(persons, is(arrayContaining(
                new Person("Алексей", "Доренко", 40),
                new Person("Артем", "Зимов", 45),
                new Person("Николай", "Зимов", 30),
                new Person("Иван", "Мельников", 20)
        )));
    }

    @Test
    void findFirstWithAge30UsingGuavaPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        // TODO use FluentIterable
        Person person = FluentIterable.from(persons).firstMatch(person1 -> person1.getAge() == 30).get();
        //persons.stream().filter(person1 -> person1.getAge() == 30).findFirst()

        assertThat(person, is(new Person("Николай", "Зимов", 30)));
    }

    @Test
    void findFirstWithAge30UsingGuavaAnonymousPredicate() {
        List<Person> persons = Arrays.asList(getPersons());

        // TODO use FluentIterable
        Person person = FluentIterable.from(persons).firstMatch(new Predicate<Person>() {
            @Override
            public boolean apply(Person person) {
                return person.getAge() == 30;
            }
        }).get();

        assertThat(person, is(new Person("Николай", "Зимов", 30)));
    }

    private Person[] getPersons() {
        return new Person[]{
                new Person("Иван", "Мельников", 20),
                new Person("Алексей", "Доренко", 40),
                new Person("Николай", "Зимов", 30),
                new Person("Артем", "Зимов", 45)
        };
    }
}
