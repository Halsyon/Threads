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
        Thread progress = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println("start ...");
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().isInterrupted());
                            System.out.println(Thread.currentThread().getState());
                        }
                    }
                }
        );
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
        progress.join();
    }
}
