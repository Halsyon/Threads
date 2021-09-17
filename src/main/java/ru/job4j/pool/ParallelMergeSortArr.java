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
 * public <T> T invoke ( задача ForkJoinTask <T>) - Выполняет данную задачу,
 * возвращая результат по завершении. Если вычисление обнаруживает непроверенное исключение
 * или ошибку, оно генерируется повторно как результат этого вызова.
 * Повторно созданные исключения ведут себя так же, как и обычные исключения, но,
 * когда это возможно, содержат трассировки стека (как показано, например,
 * с помощью ex.printStackTrace()) как текущего потока, так и потока,
 * фактически столкнувшегося с исключением; минимально только последнее.
 *
 * @author SlartiBartFast-art
 * @since 14.09.2021
 */
public class ParallelMergeSortArr extends RecursiveTask<Integer> {
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
    protected Integer compute() {
        if ((to - from) <= 10) {
            for (int i = from; i <= to; i++) {
                if (array[i].equals(element)) {
                    return i;
                }
            }
            return -1;
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
        Integer left = leftSort.join();
        Integer right = rightSort.join();
        return Math.max(left, right);
    }

    public static Integer sort(Integer[] array, int element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelMergeSortArr(array, 0, array.length - 1, element));
    }
}
