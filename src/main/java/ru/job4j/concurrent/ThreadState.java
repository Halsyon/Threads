package ru.job4j.concurrent;

/**
 * 1.1. Состояние нити. [#229175]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 */
public class ThreadState {
    //в этой программе присутствуют две нити: main и first.
    // Нить main - это нить, которая запускается, когда мы запускаем метод main.
    // Нить first мы создали сами.
    public static void main(String[] args) {
        //Чтобы получить состояние нити можно воспользоваться методом - getState().
        Thread first = new Thread(
                () -> System.out.println(" first")
        );
        Thread second  = new Thread(
                () -> System.out.println(" second")
        );
        System.out.println("first : " + first.getState());
        System.out.println("second : " + first.getState());

        first.start();
       second.start();

        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println("First : " + first.getState());
        }
        while (second.getState() != Thread.State.TERMINATED) {
            System.out.println("Second : " + second.getState());
        }
        System.out.println("first : " + first.getState());
        System.out.println("second : " + second.getState());
    }
}
