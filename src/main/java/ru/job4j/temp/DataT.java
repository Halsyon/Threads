package ru.job4j.temp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class DataT {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        int i = 1;
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println("I " + i);
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Тотал тайм : " + totalTime);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(totalTime);
        //Получаем минуты/секунды (без нулей в начале):

        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);
        System.out.println("minuti : " + minutes);
        System.out.println("seconds : " + seconds);
        int finish = seconds;
        System.out.println("итого время выполнения : " + finish);
    }
}
