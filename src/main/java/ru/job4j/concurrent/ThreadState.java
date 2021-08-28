package ru.job4j.concurrent;

/**
 * 1.1. Состояние нити. [#229175]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 * 1. Поправьте класс ThreadState там образом, чтобы в нем создавалось две нити.
 * Каждая нить должна вывести свое имя на консоль.
 * 2. Нить main должна дождаться завершения этих нитей и вывести на консоль сообщение,
 * что работа завершена.
 */
public class ThreadState {
    //в этой программе присутствуют две нити: main и first.
    // Нить main - это нить, которая запускается, когда мы запускаем метод main.
    // Нить first мы создали сами.
    public static void main(String[] args) {
        //Чтобы получить состояние нити можно воспользоваться методом - getState().
        Thread first = new Thread(
                // В конструкторе нового объекта задайте вывод на консоль имени новой нити.
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                // В конструкторе нового объекта задайте вывод на консоль имени новой нити.
                () -> System.out.println(Thread.currentThread().getName())
        );
        System.out.println("first : " + first.getState());
        System.out.println("second : " + first.getState());
//Метод Thread#start() указывает виртуальной машине,
// что операторы описанные в конструкторе нужно запустить в отдельной нити.
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println("First : " + first.getState() + "Second : " + second.getState());
        }
        System.out.println("the current thread " + Thread.currentThread().getName()
                + "  first.getState() " + first.getState()
                + "  second.getState() " + second.getState());
    }
}
