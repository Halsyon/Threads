package ru.job4j.pool;

/**
 * 1. Реализовать ThreadPool [#1099]
 * Уровень : 3. Мидл Категория : 3.1.
 * Multithreading Топик : 3.1.6. Пулы
 * @author SlartiBartFast-art
 * @since 11.09.2021
 */
public class Job implements Runnable {
    private int num;

    public Job(int n) {
        num = n;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Task " + num + " is running.");
        }
        Thread.currentThread().interrupt();
        System.out.println("Job IS Done :) " + Thread.currentThread().getState());
    }
}
