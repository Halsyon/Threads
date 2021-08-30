package ru.job4j.commonresources;

/**
 * 2. Модель памяти Java [#267917]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.2. Общие ресурсы
 */
public final class DCLSingleton {

    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }
}
