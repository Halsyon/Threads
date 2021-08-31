package ru.job4j.buffer;

/**
 * 0. Объект монитор, критическая секция [#6857 #196743]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 * Пример
 */
public class Buffer {
    private StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        System.out.print(value);
        buffer.append(value);
    }

    @Override
    public synchronized String toString() {
        return buffer.toString();
    }
}
