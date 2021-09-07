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
 * пока друггой поток не вызовет метод notify();
 *synchronize идет по объекту с аннотацией  @GuardedBy("this") - объект монитора
 * @param <T> generic type
 * @author SlartiBartFast-art
 * @version 0.5
 * @since 03.09.2021
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();  //блокирующая очередь ограниченная по размеру
//поле с  типом int, которое будет ограничивать очередь сверху
    private int count = 0;

    /**
     * Метод poll() должен вернуть объект из внутренней коллекции.
     * Если в коллекции объектов нет, то нужно перевести текущую нить в состояние ожидания.
     * Важный момент, когда нить переводить в состояние ожидания, то она отпускает объект монитор
     * и другая нить тоже может выполнить этот метод.
     *
     * @return Object T or null
     */
    public synchronized T poll() throws InterruptedException { // Consumer извлекает данные из очереди
        // peek()- голова этой двухсторонней очереди, или null, если эта двухсторонняя очередь пуста
        //while (queue.peek() == null) {
        while (count < 1) {
//wait() - выбрасывает InterruptedException поэтому работаем с ним в блоке try-catch
            queue.wait(); // перевести текущую нить в состояние ожидания.
        }
        count--;
//notify() - не освобождает монитор и будит поток, у которого ранее был вызван метод wait();
        queue.notify(); // она отпускает объект монитор
        return queue.poll();
    }

    public synchronized void offer(T value) throws InterruptedException { // Producer помещает данные в очередь
//queue.offer(value) Немедленное размещение элемента в очереди при наличие свободного места;
// метод вернет true при успешном завершении операции, в противном случае вернет false.
//поле count - искуственное ограничение очереди размером в 5 элементов,
// если оно == или превышает ставим текущий поток в ожидание
        while (count >= 5) {
//Для того чтобы нить перевести в ждущее состояние необходимо в ее процессе вызвать метод wait()
// для объекта монитора.
            queue.wait();
        }
        queue.offer(value);
        count++;
        queue.notify();
    }
}




