package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;

/**
 * 3. ForkJoinPool [#315067]
 * Уровень : 3. Мидл Категория : 3.1.
 * Multithreading Топик : 3.1.6. Пулы
 * Пример использования ForkJoinPool
 */
public class Temp1 {
    public static void main(String[] args) {
        //Получение пула с готовыми настройками
        ForkJoinPool forkJoinPool1 = ForkJoinPool.commonPool();

        //2. Создание отдельного пула
        ForkJoinPool forkJoinPool2 = new ForkJoinPool();
    }
}
