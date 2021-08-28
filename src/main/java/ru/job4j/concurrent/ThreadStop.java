package ru.job4j.concurrent;

/**
 * 3. Прерывание нити [#1019]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 * Если запустить этот код несколько раз,
 * то в консоли можно увидеть разное количество чисел.
 */
public class ThreadStop {
    //В свое очередь во второй нити идет проверка этого флага.
    //while (!Thread.currentThread().isInterrupted()) {
    //Если он выставлен, то мы не заходим больше в тело цикла и выходим из метода run().
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(
                () -> {
                    int count = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(count++);
                    }
                }
        );
        thread.start();
        System.out.println("до сна______________-------------------");
        Thread.sleep(1000);
        System.out.println("Спать 1000 мил / иначе 1 секунда");
        thread.interrupt();
    }
}
