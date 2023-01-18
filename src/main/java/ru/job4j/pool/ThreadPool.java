package ru.job4j.pool;

import ru.job4j.waitnotify3j1.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. Реализовать ThreadPool [#1099]
 *
 * Multithreading Топик : 3.1.6. Пулы
 * tasks - это блокирующая очередь. Если в очереди нет элементов,
 * то нить переводиться в состоянии Thread.State.WAITING.
 * Когда приходит новая задача, всем нитям в состоянии Thread.State.WAITING посылается сигнал
 * проснуться и начать работу.
 * run() - Когда объект, реализующий интерфейс Runnable, используется для создания потока,
 * запуск потока вызывает run вызов метода объекта в этом отдельно выполняемом потоке.
 * int size = Runtime.getRuntime().availableProcessors()
 * -Инициализация пула должна быть по количеству ядер в системе.
 * @since 11.09.2021
 */
public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<Thread>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() throws InterruptedException {
        System.out.println("SIZE :" + size);
        for (int i = 0; i < size; i++) {
            threads.add(i, new Thread(tasks.poll()));
            threads.get(i).start();
        }
    }

    /**
     * метод work(Runnable job) - этот метод должен добавлять задачи в блокирующую очередь tasks.
     * tasks - это блокирующая очередь. Если в очереди нет элементов,
     * * то нить переводиться в состоянии Thread.State.WAITING.
     *
     * @param job exercise
     */
    public synchronized void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * метод shutdown() - этот метод завершит все запущенные задачи.
     * выставить флаг на завершение потока
     */
    public synchronized void shutdown() throws InterruptedException {
        for (Thread t : threads) {
            if (t.isAlive()) {
                t.interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Job job = new Job(0);
        Job job1 = new Job(1);
        Job job2 = new Job(2);
        Job job3 = new Job(3);
        Job job4 = new Job(4);

        threadPool.work(job);
        threadPool.work(job1);
        threadPool.work(job2);
        threadPool.work(job3);
        threadPool.work(job4);

        threadPool.shutdown();
    }
}
