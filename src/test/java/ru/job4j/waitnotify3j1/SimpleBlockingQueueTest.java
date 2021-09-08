package ru.job4j.waitnotify3j1;

import org.junit.Test;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * 3. Junit тест для блокирующей очереди. [#68589]
 * Уровень : 3. МидлКатегория : 3.1. Multithreading
 * Топик : 3.1.4. Wait, Notify, NotifyAll
 * написать полноценный тест на блокирующую очередь.
 * @author SlartiBartFast-art
 * @since 09.09.2021
 */
public class SimpleBlockingQueueTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            value -> {
                                try {
                                    queue.offer(value);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

    @Test
    public void whenGetIt() {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(14);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 7).forEach(
                            value -> {
                                try {
                                    System.out.println("value : " + value);
                                    queue.offer(value);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                }
        );

        Thread consumer = new Thread(
                () -> {
                    System.out.println(!Thread.currentThread().isInterrupted());
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        System.out.println(!queue.isEmpty());

                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        try {
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consumer.start();
        System.out.println("consumer.getState()" + consumer.getState());
        consumer.interrupt();
        try {
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4, 5, 6)));
    }
   /* @Test
    public void test() throws InterruptedException {
        var queue = new SimpleBlockingQueue<Integer>(3);
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(2);
                        Thread.sleep(3000);
                        queue.offer(3);
                        Thread.sleep(3000);
                        queue.offer(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Producer"
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                        Thread.sleep(1000);
                        queue.poll();
                        Thread.sleep(1000);
                        queue.poll();
                        Thread.sleep(1000);
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                "Consumer"
        );
        System.out.println("Program start");
        queue.offer(1);
        System.out.println(producer.getName() + " - STARTED");
        producer.start();
        System.out.println(consumer.getName() + " - STARTED");
        consumer.start();
        try {
            System.out.println("main - sleep - 1000 ms");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main - AWAKE");
        System.out.println("Main - join others");
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main - finish work");
        System.out.println("Program finish");
    }*/

}