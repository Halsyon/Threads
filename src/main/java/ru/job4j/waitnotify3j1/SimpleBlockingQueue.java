package ru.job4j.waitnotify3j1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Реализовать шаблон Producer Consumer. [#1098]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.4.
 * Если очередь заполнена полностью,то при попытке добавления поток Producer блокируется,
 * до тех пор пока Consumer не извлечет очередные данные,т.е.в очереди появится свободное место.
 * И наоборот если очередь пуста поток Consumer блокируется,до тех пор пока Producer не поместит
 * в очередь данные.
 * Важно !  - на мониторе какого объекта вы делает синхронизацию, на томже самом объекте
 * вы должны делать wait() и notify()
 * wait() - освобождает монитор и переводит вызывающий поток в состояние ожидания до тех пор,
 * пока другой поток не вызовет метод notify();
 * synchronize идет по объекту с аннотацией  @GuardedBy("this") - объект монитора
 * private Queue<T> queue = new LinkedList<>() -блокирующая очередь ограниченная по размеру
 * count - поле с  типом int, которое будет ограничивать очередь сверху
 *
 * @param <T> generic type
 * @author SlartiBartFast-art
 * @since 03.09.2021
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int count = 42;

    public SimpleBlockingQueue() {
    }

    public SimpleBlockingQueue(int count) {
        this.count = count;
    }

    /**
     * Метод проводит немедленное размещение элемента в очереди при наличие свободного места;
     * Суть - Producer помещает данные в очередь
     * queue.offer(value) Немедленное размещение элемента в очереди при наличие свободного места,
     * метод вернет true при успешном завершении операции, в противном случае вернет false.
     * поле count - искуственное ограничение очереди размером заданным при создании Очереди,
     * если оно == или превышает ставим текущий поток в ожидание
     * Для того чтобы нить перевести в ждущее состояние необходимо в ее процессе вызвать метод wait()
     * для объекта монитора.
     *
     * @param value любое значение заданное в качестве объекта очереди
     * @throws InterruptedException возможное исключение в методе в случае вызова метода wait()
     */
    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == count) {
            wait();
        }
        queue.offer(value);
        notify();
    }

    /**
     * Метод poll() должен вернуть объект из внутренней коллекции.
     * Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания.
     * Важный момент, когда нить переводить в состояние ожидания, то она отпускает объект монитор
     * и другая нить тоже может выполнить этот метод.
     * Consumer извлекает данные из очереди
     * wait(); // перевести текущую нить в состояние ожидания.
     * wait() - выбрасывает InterruptedException поэтому работаем с ним в блоке try-catch
     * notify(); // она отпускает объект монитор
     * notify() - не освобождает монитор и будит поток, у которого ранее был вызван метод wait();
     * peek()- голова этой двухсторонней очереди, или null, если эта двухсторонняя очередь пуста
     *
     * @return Object T or null
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        var t = queue.poll();
        notify();
        return t;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return "SimpleBlockingQueue{"
                + "queue=" + queue
                + ", count=" + count
                + '}';
    }
}




