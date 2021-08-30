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
    private final String fileName;
    private final int speed;

    public WgetF(String url, String fileName, int speed) {
        this.url = url;
        this.fileName = fileName;
        this.speed = speed;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public int getSpeed() {
        return speed;
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

    /**
     * Метод проводит скачивание файла из сети, при скачивании проводиться
     * ограничение скорости в случае превышения заданного порога
     * скачивание возможно как в файл по умолчанию так и в указанный пользователем файл
     */
    @Override
    public void run() {
        long startTime;
        //String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            // System.out.println("URL :" + url);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long totalTime = System.currentTimeMillis() - startTime;
                //System.out.println("totalTime : " + totalTime);
                if (totalTime < speed) {
                    try {
                        int time = (int) totalTime * 1000;
                        //System.out.println("Time sleep milliseconds " + time);
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                startTime = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
// скачивание без указания имени файла в аргументах
        /*String url = args[0];
        int speed = Integer.parseInt(args[1]);
        if (args.length < 2) {
            throw new IllegalArgumentException("You may not have specified one of the arguments");
        }
        Thread wget = new Thread(new WgetF(url, speed));
        wget.start();
        wget.join();*/

// вариант в случае указывания пользователем файла в который мы будем скачивать
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String nameF = args[2];
        if (args.length < 3) {
            throw new IllegalArgumentException("You may not have specified one of the arguments");
        }
        Thread wget = new Thread(new WgetF(url, nameF, speed));
        wget.start();
        wget.join();
    }
}
