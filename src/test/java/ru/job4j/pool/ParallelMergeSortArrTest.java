package ru.job4j.pool;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelMergeSortArrTest {
    @Test
    public void whenAddThenGet() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println(arr.length);
        ParallelMergeSortArr mergeSortArr = new ParallelMergeSortArr(arr, 0, arr.length, 3);
        Integer[] arr1 = {3};
        var f = mergeSortArr.compute();
        assertThat(f, Is.is(arr1));
    }

    @Test
    public void whenAdd9() {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ParallelMergeSortArr mergeSortArr = new ParallelMergeSortArr(arr, 0, arr.length, 5);
        var f = mergeSortArr.compute();
        Integer[] arr1 = {5};
        assertThat(f, Is.is(arr1));
    }

    @Test
    public void whenAdd30() {
        Integer[] arr = new Integer[30];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        ParallelMergeSortArr mergeSortArr = new ParallelMergeSortArr(arr, 0, arr.length, 11);
        Integer[] arr1 = {11};
        var f = mergeSortArr.compute();
        assertThat(f, Is.is(arr1));
    }
}