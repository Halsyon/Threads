package ru.job4j.pool;

import java.lang.reflect.Array;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 3. ForkJoinPool [#315067]
 * Уровень : 3. Мидл Категория : 3.1.
 * Multithreading Топик : 3.1.6. Пулы
 * Реализовать параллельный поиск индекса в массиве объектов. В целях оптимизации,
 * если размер массива не больше 10, использовать обычный линейный поиск.
 * Метод поиска должен быть обобщенным.
 * @author SlartiBartFast-art
 * @since 14.09.2021
 */
public class ParallelMergeSortArr extends RecursiveTask<int[]> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int index;

    public ParallelMergeSortArr(int[] array, int from, int to, int index) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.index = index;
    }

    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] {array[from]};
        }
        if (array.length <= 10) {
            for (int i = 0; i < array.length; i++) {
                if (i == index) {
                    return new int[] {array[i]};
                }
            }
        }
        int mid = (from + to) / 2;
        // создаем задачи для сортировки частей
        ParallelMergeSortArr leftSort = new ParallelMergeSortArr(array, from, mid, index);
        ParallelMergeSortArr rightSort = new ParallelMergeSortArr(array, mid + 1, to, index);
        // производим деление.
        // оно будет происходить, пока в частях не останется по одному элементу
        leftSort.fork();
        rightSort.fork();
        // объединяем полученные результаты
        int[] left = leftSort.join();
        int[] right = rightSort.join();
        return MergeSort.merge(left, right);

    }

    public static int[] sort(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSortArr(array, 0, array.length - 1, 3));
    }
}
