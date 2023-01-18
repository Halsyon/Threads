package ru.job4j.pool;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 *  CompletableFuture [#361626]
 * @since 17.09.2021
 */
public class RolColSumTest {
    private int[][] str;
    private int[][] str1;

    @Before
    public void init() {
        str = new int[3][3];
        str[0][0] = 1;
        str[0][1] = 2;
        str[0][2] = 3;
        str[1][0] = 4;
        str[1][1] = 5;
        str[1][2] = 6;
        str[2][0] = 7;
        str[2][1] = 8;
        str[2][2] = 9;
        str1 = new int[2][3];
        str1[0][0] = 1;
        str1[0][1] = 2;
        str1[0][2] = 3;
        str1[1][0] = 4;
        str1[1][1] = 5;
        str1[1][2] = 5;
    }

    @Test
    public void whenAsyncSum1() throws ExecutionException, InterruptedException {
        String d = "[Sums{rowSum=6, colSum=12}, Sums{rowSum=15, colSum=15}, Sums{rowSum=24, colSum=18}]";
        assertThat(Arrays.toString(RolColSum.asyncSum(str)), Is.is(d));
    }

    @Test
    public void whenAsyncSum2() throws ExecutionException, InterruptedException {
        String d = "[Sums{rowSum=3, colSum=5}, Sums{rowSum=9, colSum=7}]";
        assertThat(Arrays.toString(RolColSum.asyncSum(str1)), Is.is(d));
    }

    @Test
    public void whenSum() {
        String d = "[Sums{rowSum=6, colSum=12}, Sums{rowSum=15, colSum=15}, Sums{rowSum=24, colSum=18}]";
        assertThat(Arrays.toString(RolColSum.sum(str)), Is.is(d));
    }
}
