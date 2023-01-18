package ru.job4j.cas;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 0. CAS - операции [#6859]
 * 3.1. Multithreading
 * Топик : 3.1.5. Non Blocking Algoritm
 * Реализовать неблокирующий счетчик.
 * @since 09.09.2021
 */
public class CASCountTest {
    @Test
    public void whenAddThenGet() {
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.increment();
        Integer expect = casCount.get();
        assertThat(expect, Is.is(2));
    }

  /*  /**
     * Роверка получвения значения при создании класса
     * и пустом AtomicReference<Integer> count
     */
  /*
  @Test(expected = UnsupportedOperationException.class)
    public void whenEmpty() {
        CASCount casCount = new CASCount();
        casCount.get();
    }*/
}
