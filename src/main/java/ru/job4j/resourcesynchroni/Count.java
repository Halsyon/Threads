package ru.job4j.resourcesynchroni;

/**
 * 0. Объект монитор, критическая секция [#6857 #196743]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 * Перепишем этот код для наглядности определения термина объект монитор.
 */
public class Count {
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
}
