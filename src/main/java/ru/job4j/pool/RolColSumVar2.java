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
 * 1. Дан каркас класса. Ваша задача подсчитать суммы по строкам и столбцам матрицы.
 * - sums[i].rowSum - сумма элементов по i строке
 * - sums[i].colSum  - сумма элементов по i столбцу
 * asyncSum(int[][] matrix)/sum(int[][] matrix) - Эти методы должны посчитать одно и тоже
 * - сумму строки и столбца в массиве
 * вариант 2
 * rowSum - значение элемента по оси Х
 * colSum - значение элемента по оси У
 * объект типа CompletableFuture, который вернет результат и которой просто выполнит действие,
 * не возвращая результата. Для этой цели служат методы supplyAsync() и runAsync() соответственно.
 * Первый возвращает CompletableFuture<T>, второй CompletableFuture<Void>. Стоит отметить,
 * что создание предполагает и запуск. Оба этих метода запускают асинхронную задачу.
 * @author SlartiBartFast-art
 * @since 18.09.2021
 */
public class RolColSumVar2 {
    /**
     * calculates the sum of the elements along the x-axis and y-axis
     *
     * @param matrix two-dimensional array
     * @param row    integer
     * @return Object Sums
     */
    private static Sums countSum(int[][] matrix, int row) {
        int rol = 0;
        int col = 0;
        Sums sums = new Sums();
        for (int i = 0; i < matrix.length; i++) {
            rol += matrix[row][i];
            col += matrix[i][row];
        }
        sums.setRowSum(rol);
        sums.setColSum(col);
        return sums;
    }

    /**
     * Method return sum integer
     *
     * @param matrix array integer
     * @return array Object Sums
     */
    public static Sums[] sum(int[][] matrix) {
        Sums[] sumsMatrix = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sumsMatrix[i] = countSum(matrix, i);
        }
        return sumsMatrix;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sumsMatrix = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (int i = 0; i < sumsMatrix.length; i++) {
            sumsMatrix[i] = futures.get(i).get();
        }
        return sumsMatrix;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int row) {
        return CompletableFuture.supplyAsync(() -> countSum(data, row));
    }

    public static class Sums {
        private int rowSum;
        private int colSum;

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
}
