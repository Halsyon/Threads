package ru.job4j.pool;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 3. ForkJoinPool [#315067]
 * Multithreading Топик : 3.1.6. Пулы
 * Реализовать параллельный поиск индекса в массиве объектов. В целях оптимизации,
 * если размер массива не больше 10, использовать обычный линейный поиск.
 * Метод поиска должен быть обобщенным.
 * @since 14.09.2021
 */
public class ParallelMergeSortArrTest {

    @Test
    public void when1() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Integer i = 2;
        var f = ParallelMergeSortArr.sort(arr, 3);
        assertThat(f, Is.is(i));
    }

    @Test
    public void when11() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Integer i = 10;
        var f = ParallelMergeSortArr.sort(arr, 11);
        assertThat(f, Is.is(i));
    }

    @Test
    public void when30() {
        Integer[] arr = new Integer[30];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Integer i = 17;
        var f = ParallelMergeSortArr.sort(arr, 17);
        assertThat(f, Is.is(i));
    }


    //    тест, в котором метод ничего не найдет. В этом случае он вернет -1
    @Test
    public void whenNoResult() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        Integer i = -1;
        var f = ParallelMergeSortArr.sort(arr, 17);
        assertThat(f, Is.is(i));
    }
}
