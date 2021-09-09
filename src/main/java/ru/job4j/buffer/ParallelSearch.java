package ru.job4j.buffer;

import ru.job4j.waitnotify3j1.SimpleBlockingQueue;

/**
 * 2. Обеспечить остановку потребителя. [#66825]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading
 * Топик : 3.1.4. Wait, Notify, NotifyAll
 * В этом задании нужно разработать механизм остановки потребителя,
 * когда производитель закончил свою работу.
 * Изменить код, так, что бы потребитель завершал свою работу.
 * Код нужно изменить в методе main.
 *
 * @author SlartiBartFast-art
 * @since 07.09.2021
 */
public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        producer.start();
        producer.join();
        consumer.start();
        consumer.interrupt();
    }
}