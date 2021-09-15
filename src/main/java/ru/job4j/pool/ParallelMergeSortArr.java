package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 3. ForkJoinPool [#315067]
 * Уровень : 3. Мидл Категория : 3.1.
 * Multithreading Топик : 3.1.6. Пулы
 * Реализовать параллельный поиск индекса в массиве объектов. В целях оптимизации,
 * если размер массива не больше 10, использовать обычный линейный поиск.
 * Метод поиска должен быть обобщенным.
 *
 * @author SlartiBartFast-art
 * @since 14.09.2021
 */
public class ParallelMergeSortArr extends RecursiveTask<Integer[]> {
    private final Integer[] array;
    private final int from;
    private final int to;
    private final int element;

    public ParallelMergeSortArr(Integer[] array, int from, int to, int element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer[] compute() {
        if ((to - from) <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(element)) {
                    System.out.println(new Integer[]{array[i]}.length + " ДЛИНА");
                    return new Integer[]{array[i]};
                }
            }
            return new Integer[]{-1};
        }
        int mid = (from + to) / 2;
        // создаем задачи для сортировки частей
        ParallelMergeSortArr leftSort = new ParallelMergeSortArr(array, from, mid, element);
        ParallelMergeSortArr rightSort = new ParallelMergeSortArr(array, mid + 1, to, element);
        // производим деление.
        // оно будет происходить, пока в частях не останется по одному элементу
        leftSort.fork();
        rightSort.fork();
        // объединяем полученные результаты
        Integer[] left = leftSort.join();
        Integer[] right = rightSort.join();
       var f = revers(MergeSort.merge(coup(left), coup(right)));
        for (int i = 0; i < f.length; i++) {
            if (f[i] > 0) {
                return new Integer[]{f[i]};
            }
        }
        return new Integer[]{-1};
    }

    private int[] coup(Integer[] array) {
        int[] arr2 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr2[i] = array[i];
        }
        return arr2;
    }

    private Integer[] revers(int[] array) {
        Integer[] integers = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            integers[i] = array[i];
        }
        return integers;
    }

    public static Integer[] sort(Integer[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSortArr(array, 0, array.length - 1, 3));
    }
}
