package streams.part2.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class Exercise5 {


    /**
     * Выбирает из текста наиболее часто встречающиеся слова.
     * Подсчет слов выполняется без учета регистра
     * Если слова имею одинаковую частоту, они выводятся в лексиграфическом порядке
     *
     * @param text
     * @param numberWords
     * @return список оторбранных слов
     */
    private List<String> getFrequentlyOccurringWords(String text, int numberWords) {
        return Pattern.compile("\\s+")
                .splitAsStream(text)
                .map(s -> s.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", ""))
                .filter(((Predicate<String>) String::isEmpty).negate())
                .map(String::toLowerCase)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long> >comparingLong(Map.Entry::getValue).thenComparing(Map.Entry::getKey))
                .limit(numberWords)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Test
    void test1() {
        String source = "Мама мыла мыла мыла раму";
        List<String> result = getFrequentlyOccurringWords(source, 5);
        assertThat(result, contains("мыла", "мама", "раму"));
    }

    @Test
    void test2() {
        String source = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? Praesent dapibus. Ut tempus purus at lorem. Aenean fermentum risus id tortor. Donec vitae arcu. Nullam at arcu a est sollicitudin euismod. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sed, est. Etiam posuere lacus quis dolor. Pellentesque arcu. ";
        List<String> result = getFrequentlyOccurringWords(source, 5);
        assertThat(result, contains());
    }
}
