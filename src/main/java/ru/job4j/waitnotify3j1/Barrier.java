package ru.job4j.waitnotify3j1;

/**
 * 0. Управление нитью через wait. [#6858]01
 * Уровень : 3. МидлКатегория : 3.1. Multithreading Топик : 3.1.4. Wait, Notify, NotifyAll
 * пример часть 1
 */
public class Barrier {
//Переменная flag - это общий ресурс, поэтому мы с ней работаем  только в  критической секции.
    private boolean flag = false;
//строчка кода сделана для наглядности объекта монитора.
    private final Object monitor = this;

    /**
     * Метод on и off меняют флаг с true на false.
     * После каждого изменения программа будит нити, которые ждут изменений.
     */
    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
//Метод notifyAll будит все нити, которые ждали изменения состояния.
        }
    }

    /**
     * Метод on и off меняют флаг с true на false.
     * После каждого изменения программа будит нити, которые ждут изменений.
     */
    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    /**
     * Когда нить заходит в метод check, то она проверяет flag.
     * Если флаг = false, то нить засыпает.
     */
    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
//Метод wait переводит нить в состояние ожидания, если программа не может дальше выполняться.
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
