package ru.job4j.net;

import java.io.*;
import java.net.URL;

/**
 * 4. Скачивание файла с ограничением. [#144271]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.1. Threads
 * Пример кода для скачивания файла с задержкой в одну секунду.
 */
public class FileDownload {
    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                long totalTime = System.currentTimeMillis() - startTime;
                System.out.println("Тотал тайм : " + totalTime);
                System.out.println("число по итогу " + bytesRead);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
