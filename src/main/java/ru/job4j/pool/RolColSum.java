package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture [#361626]
 * MultithreadingТопик :  Пулы
 * Пример использования
 * 1. Дан каркас класса. Ваша задача подсчитать суммы по строкам и столбцам матрицы.
 * - sums[i].rowSum - сумма элементов по i строке
 * - sums[i].colSum  - сумма элементов по i столбцу
 *  @since 16.09.2021
 */
public class RolColSum {

    public static class Sums {
        private int rowSum; //rowSum - значение элемента по i строке
        private int colSum; //rowSum - значение элемента по i столбцу

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }
    }

    private static Sums[] countSum(int[][] matrix) {
        int n = matrix.length;
        // создаем массив по кол-ву элем-в Sums на размер матрицы
        Sums[] sumsMatrix = new Sums[n];
        for (int i = 0; i < matrix.length; i++) {
            sumsMatrix[i] = new Sums();
        }
        int col = 0; // строка
        int cll = 0; // строка
        do {
            int sum1 = 0; // сумма1
            int sum2 = 0; // сумма2
            for (int i = 0; i < n; i++) {
                sum1 += matrix[col][i];
                if (cll != n) {
                    for (int j = 0; j < n; j++) {
                        sum2 += matrix[j][i];
                    }
                    sumsMatrix[cll++].setColSum(sum2); // записали столбец 1-й, 2, 3
                    sum2 = 0;
                }
            }
            sumsMatrix[col].setRowSum(sum1); //записал сумму 1-й строки, 2, 3
            col++;
        } while (col != n);
        return sumsMatrix; // вернули массив из Object Sums
    }

    //sums[i].rowSum - сумма элементов по i строке
    //Эти методы должны посчитать одно и тоже - сумму строки и столбца в массиве
    public static Sums[] sum(int[][] matrix) {
        return countSum(matrix);
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] sumsMatrix = new Sums[n];
        Map<Integer, CompletableFuture<Sums[]>> futures = new HashMap<>();
        futures.put(0, getTask(matrix));
        for (Integer key : futures.keySet()) {
            var t = futures.get(key).get();
            for (int i = 0; i < t.length; i++) {
                sumsMatrix[i] = t[i];
            }
        }
        return sumsMatrix;
    }

    public static CompletableFuture<Sums[]> getTask(int[][] data) {
        return CompletableFuture.supplyAsync(() -> countSum(data));
    }
}
