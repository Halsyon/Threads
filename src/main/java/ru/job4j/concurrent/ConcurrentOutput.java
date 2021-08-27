package ru.job4j.concurrent;

/**
 * 1. Запуск нити. Thread#start() [#1016]00
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 */
public class ConcurrentOutput {
    //Thread.currentThread().getName() - Этот статический метод позволяет получить экземпляр
// текущей нити выполнения. То есть той нити в который выполняется этот оператор.
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
      /*  Thread another = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
        );*/
        Thread second  = new Thread(
                // В конструкторе нового объекта задайте вывод на консоль имени новой нити.
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        //another.run();
        System.out.println(Thread.currentThread().getName());
    }
}
