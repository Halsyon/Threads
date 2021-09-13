package ru.job4j.cache;

import org.hamcrest.core.Is;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * 1. Неблокирующий кеш [#4741]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading
 * Топик : 3.1.5. Non Blocking Algorithm
 * Напишите модульные тесты. Они будут не многопоточные, а последовательные.
 *
 * @author SlartiBartFast-art
 * @since 11.09.2021
 */
public class CacheTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        Base base3 = new Base(3, 0);
        cache.add(base1);
        cache.add(base2);
        cache.add(base3);
        assertThat(cache.size(), Is.is(3));
    }

    @Test
    public void whenAddThenGet() throws InterruptedException {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        cache.add(base1);
        Base ex = cache.getMemory().get(1);
        assertThat(ex, Is.is(base1));
    }

    @Test
    public void whenAddThenDel() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base2);
        Base exp = cache.getMemory().get(1);
        assertThat(exp, Is.is(base1));
    }

    @Test(expected = OptimisticException.class)
    public void whenAddThenExc() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        base2 = new Base(2, 2);
        cache.update(base2);
    }

    @Test
    public void whenAddThenUpd() {
        Map expL = new HashMap();
        Map realL = new HashMap();
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        base2 = new Base(2, 0);
        Base base3 = new Base(2, 1);
        cache.update(base2);
        expL.put(1, base1);
        expL.put(2, base3);
        realL.putAll(cache.getMemory());

        assertThat(realL,
                Is.is(expL));
    }
}