package ru.job4j.waitnotify3j1;

/**
 * 0. Управление нитью через wait. [#6858]01
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.4. Wait, Notify, NotifyAll
 * пример часть 2
 */
public class MultiUser {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.on();
                },
                "Master"
        );
        Thread slave = new Thread(
                () -> {
                    barrier.check();
                    System.out.println(Thread.currentThread().getName() + " started");
                },
                "Slave"
        );
        master.start();
        slave.start();
    }
}
