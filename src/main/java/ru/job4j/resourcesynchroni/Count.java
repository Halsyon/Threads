package ru.job4j.resourcesynchroni;

import net.jcip.annotations.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * 0. Объект монитор, критическая секция [#6857 #196743]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 * Перепишем этот код для наглядности определения термина объект монитор.
 * 2. JCIP. Настройка библиотеки [#268575]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 */
// @ThreadSafe - говорит пользователям данного класса, что класс можно использовать
// в многопоточном режиме и он будет работать правильно.
// @GuardedBy("this") - выставляется над общим ресурсом. Аннотация имеет входящий параметр.
// Он указывает на объект монитора, по которому мы будем синхронизироваться.
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int get() {
        return this.value;
    }
    /*
    private int value;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }
    */
}
