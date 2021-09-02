package ru.job4j.waitnotify3j1;

/**
 * 0. Управление нитью через wait. [#6858]
 * Уровень : 3. МидлКатегория : 3.1. Multithreading Топик : 3.1.4. Wait, Notify, NotifyAll
 * Разработайте класс, который блокирует выполнение по условию счетчика.
 *
 * @author SlartiBartFast-art
 * @version 0.1
 * @since 09.02.2021
 */
public class CountBarrier {
    //строчка кода сделана для наглядности объекта монитора.
    private final Object monitor = this;
    //условно, максимально допустимое значение счетчика
//Переменная total содержит количество вызовов метода count().
    private final int total;
    //значение условного счетчика
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    /**
     * Метод count изменяет состояние программы.
     */
    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
//monitor.notifyAll() - Метод notifyAll будит все нити, которые ждали изменения состояния.
        }
    }

    /**
     * Method do delay thread after max total integer will be more the count integer
     */
    public void await() {
        synchronized (monitor) {
            while (count >= total) {
                try {
                    monitor.wait();
//Метод wait переводит нить в состояние ожидания, если программа не может дальше выполняться.
                } catch (InterruptedException e) {
//e.printStackTrace(); Idea выдает в стандарте, но с такой командой будет ошибка компиляции
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
