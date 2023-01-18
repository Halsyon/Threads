package ru.job4j.cas;

import net.jcip.annotations.NotThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 0. CAS - операции [#6859]
 * * Реализовать неблокирующий счетчик.
 * Для работы в многопоточной среде без блокировок используем атомарную ссылку AtomicReference,
 * которая обеспечит хранение целочисленного значения типа java.math.BigInteger.
 * Метод compareAndSet атомарный.
 * Это значит если две нити прочитали одно и тоже значение ref,
 * то первый вызов compareAndSet даст true, а второй вызов вернет false.
 * Вторая нить будет заново читать ref и выполнять операцию compareAndSet.
 * Обе нити не блокируются, а выполняются параллельно.
 * throw new UnsupportedOperationException("Count is not impl."); // Подсчет не реализован
 * Java.util.concurrent.atomic.AtomicBoolean.compareAndSet () — это встроенный метод в java,
 * который устанавливает значение для переданного значения в параметре, если текущее значение
 * равно ожидаемому значению, которое также передается в параметре. Функция возвращает логическое
 * значение, которое дает нам представление, было ли обновление выполнено или нет.
 * @since 09.09.2021
 */
@NotThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        this.count.set(0);
    }

    /**
     * Method do incrementation integer value in AtomicReference<Integer> count
     * if AtomicReference<Integer> count reaches Integer.MAX_VALUE,
     * method do throwUnsupportedOperationException
     */
    public void increment() {
        Integer next;
        Integer current;
        do {
            current = count.get();
            next = current + 1;
        } while (!count.compareAndSet(current, next));
    }

    /**
     * Method do comeback value from AtomicReference<Integer> count
     * important - int do't be null but Integer like a class may be
     *
     * @return integer or throw UnsupportedOperationException
     */
    public int get() {
        return count.get();
    }
}
