package ru.job4j.buffer;

import ru.job4j.waitnotify3j1.SimpleBlockingQueue;

/**
 * 2. Обеспечить остановку потребителя. [#66825]
 * Уровень : 3. Мидл Категория : 3.1. MultithreadingТопик : 3.1.4. Wait, Notify, NotifyAll
 * В этом задании нужно разработать механизм остановки потребителя,
 * когда производитель закончил свою работу.
 * Изменить код, так, что бы потребитель завершал свою работу. Код нужно изменить в методе main.
 *
 * @author SlartiBartFast-art
 * @version 1
 * @since 07.09.2021
 */
public class ParallelSearch {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        Thread producer = new Thread(() -> {
// queue.offer(index); - Немедленное размещение элемента в очереди при наличие свободного места;
// метод вернет true при успешном завершении операции, в противном случае вернет false.
            try {
                for (int index = 0; index < 3; index++) {
                    System.out.println("producer # offer = " + index);
                    queue.offer(index);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.err.println("producer was interrupted while sleep");
                e.printStackTrace();
            }
        }, "producer");
        producer.start();
        Thread consumer = new Thread(() -> {
            try {
                while (producer.isAlive() || !queue.isEmpty()) {
                    System.out.println("consumer # poll = " + queue.poll());
                    Thread.sleep(1000);
                }

                System.out.println("exit while");
            } catch (InterruptedException e) {
                System.err.println("consumer was interrupted while sleep");
                e.printStackTrace();
            }
        }, "consumer");
        consumer.start();

        System.out.println("Consumer : " + consumer.getState());
        System.out.println("Producer : " + producer.getState());
    }
}
