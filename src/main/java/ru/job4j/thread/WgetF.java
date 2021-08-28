package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 4. Скачивание файла с ограничением. [#144271]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 * 1. Разработайте каркас класса Wget.
 * Программа должна скачивать файл из сети с ограничением по скорости скачки.
 * Чтобы ограничить скорость скачивания, нужно засечь время скачивания 1024 байт.
 * Если время меньше указанного, то нужно выставить паузу за счет Thread.sleep.
 * Пауза должна вычисляться, а не быть константой.
 */
public class WgetF implements Runnable {
    private final String url;
    private final int speed;

    public WgetF(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

//Считывает байты из этого входного потока байтов в указанный массив байтов,
// начиная с заданного смещения.
//Этот метод реализует общий контракт соответствующего readметода InputStreamкласса.
// В качестве дополнительного удобства он пытается прочитать как можно больше байтов,
// многократно вызывая readметод базового потока. Эта итерация readпродолжается до тех пор,
// пока не будет выполнено одно из следующих условий:
//
//Было прочитано указанное количество байтов,
//readМетод нижележащего возвращает поток -1, указывающий конец-в-файл, или
//availableМетод нижележащих возвращает поток нулю, что указывает на дальнейшие
// запросы ввода будет блокировать.
//Если первый readв базовом потоке возвращается, -1чтобы указать конец файла, этот метод возвращается -1.
// В противном случае этот метод возвращает количество фактически прочитанных байтов.

    @Override
    public void run() {
        //String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            // System.out.println("URL :" + url);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            bytesRead = in.read(dataBuffer, 0, 1024);
            long totalTime = System.currentTimeMillis() - startTime;
            while ((bytesRead != -1)) {
                // System.out.println("totalTime : " + totalTime);
                if (totalTime < speed) {
                    try {
                        int time = (int) totalTime * 1000;
                        // System.out.println("Time sleep milliseconds " + time);
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesRead = in.read(dataBuffer, 0, 1024);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new WgetF(url, speed));
        wget.start();
        wget.join();
    }
}
