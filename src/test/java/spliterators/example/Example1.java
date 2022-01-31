package spliterators.example;

import spliterators.example1.IntArraySpliterator;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.joining;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Example1 {

    @Test
    public void intArraySpliteratorTest() {
        int[] data = {1, 2, 3, 0, -1, 20, 30, 10};

        IntArraySpliterator spliterator = new IntArraySpliterator(data);

        IntStream customStream = StreamSupport.intStream(spliterator, false);

        IntStream standardStream = Arrays.stream(data);

        String result = customStream.filter(value -> value < 10)
                              .parallel()
                              .mapToObj(String::valueOf)
                              .sorted()
                              .collect(joining(", "));

        assertEquals("-1, 0, 1, 2, 3", result);
    }
}
