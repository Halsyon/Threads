package ru.job4j.cas;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 0. CAS - операции [#6859]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading
 * Топик : 3.1.5. Non Blocking Algoritm
 * Реализовать неблокирующий счетчик.
 * Для работы в многопоточной среде без блокировок используем атомарную ссылку AtomicReference,
 * которая обеспечит хранение целочисленного значения типа java.math.BigInteger.
 * Метод compareAndSet атомарный.
 * Это значит если две нити прочитали одно и тоже значение ref,
 * то первый вызов compareAndSet даст true, а второй вызов вернет false.
 * Вторая нить будет заново читать ref и выполнять операцию compareAndSet.
 * Обе нити не блокируются, а выполняются параллельно.
 * throw new UnsupportedOperationException("Count is not impl."); // Подсчет не реализован
 *
 * @author SlartiBartFast-art
 * @since 09.09.2021
 */
@NotThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    /**
     * Method do incrementation integer value in AtomicReference<Integer> count
     * if AtomicReference<Integer> count reaches Integer.MAX_VALUE,
     * method do throwUnsupportedOperationException
     */
    public void increment() {
        Integer next;
        Integer current;
        if (count.get() == null) {
            count.set(0);
        }
        do {
            current = count.get();
            System.out.println("current , " + current);
            next = current + 1;
            System.out.println("current , " + current + " " + "next : " + next);
        } while (!count.compareAndSet(current, next));

        if (current == Integer.MAX_VALUE) {
            throw new UnsupportedOperationException("Count is not impl.");
        }

    }

    /**
     * Method do comeback value from AtomicReference<Integer> count
     * important - int do't be null but Integer like a class may be
     *
     * @return integer or throw UnsupportedOperationException
     */
    public int get() {
        if (count.get() == null) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        return count.get();
    }
}
