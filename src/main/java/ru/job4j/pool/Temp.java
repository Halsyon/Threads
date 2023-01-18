package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2. ExecutorService рассылка почты. [#63097]
 * Топик : 3.1.6. Пулы
 * В JDK входит пакет concurrent в котором уже есть готовая реализация. *
 * import java.util.concurrent.ExecutorService;
 * import java.util.concurrent.Executors;
 * Пример. Создаем пул, добавляем в него задачи.
 * int size = Runtime.getRuntime().availableProcessors()
 * -Инициализация пула должна быть по количеству ядер в системе.
 * @since 13.09.2021
 */
public class Temp {
    public static void main(String[] args) {
        /*
         *Создает пул нитей по количеству доступных процессоров.
         */
        ExecutorService pool = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
        /*
         * Добавляет задачу в пул и сразу ее выполняет.
         */
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Execute " + Thread.currentThread().getName());
            }
        });
        /*
         * Закрываем пул и ждем пока все задачи завершатся.
         */
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Execute " + Thread.currentThread().getName());
    }
}

