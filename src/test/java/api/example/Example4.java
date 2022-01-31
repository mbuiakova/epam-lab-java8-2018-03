package api.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Example4 {

    @Test
    public void arraysParallelSortUsingJava8() {
        int[] values = {1, 6, 9, 4, -1, -4, 0, 2, 3};

        Arrays.parallelSort(values);

        assertArrayEquals(new int[]{-4, -1, 0, 1, 2, 3, 4, 6, 9}, values);
    }

    // (A op B) op C == A op (B op C)

    // 1 2 3 4 5 6 7 8
    // sum


    // scan: 1 3 6 10 15 21 28 36
    // reduce (identity = 0): 0 + 1 = 1
    //                                1 + 2 = 3
    //                                        3 + 3 = 6
    /**
     * @see <a href="https://habrahabr.ru/company/epam_systems/blog/247805">Алгоритм параллельного сканирования</a>
     */
    @Test
    public void arraysParallelPrefixUsingJava8() {
        int[] values = {1, 6, 9, 4, -1, -4, 0, 2, 3};

        Arrays.parallelPrefix(values, Integer::sum);

        assertArrayEquals(new int[]{1, 7, 16, 20, 19, 15, 15, 17, 20}, values);
    }

    @Test
    public void arraysParallelSetAllUsingJava8() {
        int[] values = new int[5];

        for (int i = 0; i < values.length; ++i) {
            values[i] = i;
        }

        Arrays.parallelSetAll(values, operand -> operand + 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, values);

        // TODO для всех четных 0, для нечетных 1
        Arrays.parallelSetAll(values, i -> i & 1);
        assertArrayEquals(new int[]{0, 1, 0, 1, 0}, values);
    }
}
