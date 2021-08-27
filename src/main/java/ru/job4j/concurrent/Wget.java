package ru.job4j.concurrent;

/**
 * 1.2. Режим ожидания. [#231217]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Thread
 * 1. Создайте класс ru.job4j.coccurent.Wget и метод main. *
 * 2. В методе main необходимо симулировать процесс загрузки.
 * Для этого воспользуйтесь особенностью вывода на консоль.
 * 3. Создайте нить внутри метода main. В теле метода создайте цикл от 0 до 100.
 * Через 1 секунду выводите на консоль информацию о загрузке.
 * Вывод должен быть с обновлением строки.
 */
public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        //System.out.println("Start loading ... ");
                        for (int i = 0; i < 100; i++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i + "%");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
