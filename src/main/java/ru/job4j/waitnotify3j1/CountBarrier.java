package ru.job4j.waitnotify3j1;

/**
 * 0. Управление нитью через wait. [#6858]
 * @version 0.2
 * @since 02.09.2021
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
            while (count < total) {
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
