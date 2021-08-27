package ru.job4j.concurrent;

/**
 * 1.2. Режим ожидания. [#231217]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 * Выполнение нити можно приостановить в самой программе.
 * Это может понадобиться для ограничения скорости работы вашей программы.
 * В классе Thread есть статический метод sleep. *
 * Thread.sleep(millisecond);Этот метод переводит нить в состояние TIMED_WAITING.
 */
public class ThreadSleep {
    /**
     * напишем программу, которая ждет 3 секунды и печатает на консоль слово loaded.
     * @param args параметры
     */
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        Thread.sleep(3000);
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
