package ru.job4j.commonResources;

/**
 * 1. Синхронизация общих ресурсов. [#1096]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.2. Общие
 */
public final class Cache {
    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public synchronized static Cache getCache() {
        return cache;
    }
}
