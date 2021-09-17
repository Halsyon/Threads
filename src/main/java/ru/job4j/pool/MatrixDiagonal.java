package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 4. CompletableFuture [#361626]
 * Уровень : 3. Мидл Категория : 3.1.
 * MultithreadingТопик : 3.1.6. Пулы
 * Пример использования
 * CompletableFuture.runAsync()
 * CompletableFuture.supplyAsync()
 * Пусть нам нужно посчитать суммы элементов по диагоналям матрицы.
 *
 * @author SlartiBartFast-art
 * @since 16.09.2021
 */
public class MatrixDiagonal {
    public static int[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        int[] sums = new int[2 * n];
        Map<Integer, CompletableFuture<Integer>> futures = new HashMap<>();
        // считаем сумму по главной диагонали
        futures.put(0, getTask(matrix, 0, n - 1, n - 1));
        // считаем суммы по побочным диагоналям
        for (int k = 1; k <= n; k++) {
            futures.put(k, getTask(matrix, 0, k - 1,  k - 1));
            if (k < n) {
                futures.put(2 * n - k, getTask(matrix, n - k, n - 1, n - 1));
            }
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Integer> getTask(int[][] data, int startRow, int endRow, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = startRow; i <= endRow; i++) {
                sum += data[i][col];
                col--;
            }
            return sum;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[][] str = new int[3][3];
        str[0][0] = 1;
        str[0][1] = 2;
        str[0][2] = 3;
        str[1][0] = 4;
        str[1][1] = 5;
        str[1][2] = 6;
        str[2][0] = 7;
        str[2][1] = 8;
        str[2][2] = 9;
        var t = MatrixDiagonal.asyncSum(str);
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
        }

    }
}
