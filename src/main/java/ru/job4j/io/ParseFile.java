package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Predicate;

/**
 * * 1. Visibility. Общий ресурс вне критической секции [#1102]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация
 * Поправьте код с ошибками в коде.
 * - Избавиться от get set за счет передачи File в конструктор.
 * - Ошибки в многопоточности. Сделать класс Immutable. Все поля final.
 * - Ошибки в IO. Не закрытые ресурсы. Чтение и запись файла без буфера.
 * - Нарушен принцип единой ответственности. Тут нужно сделать два класса.
 * - Методы getContent написаны в стиле копипаста.
 * Нужно применить шаблон стратегия. content(Predicate<Character> filter)
 */
public class ParseFile implements Parse {
    private final File file; //все поля отмечены финал

    public ParseFile(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) {
        String output = "";
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = bufferedInputStream.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static void main(String[] args) {
        File file  = new File("pom_tmp.xml");
        String string = "Столица автоматически переходит в Васюки. Сюда приезжает правительство.";
        ParseFile parseFileF = new ParseFile(file);
        ParseFileSave parseFileSave = new ParseFileSave(file);
        Predicate<Character> filter = character -> character < 0x80;
        Predicate<Character> filterN = character -> true;
        var itogo = parseFileF.content(filter);
        System.out.println(itogo);
        var itogo2 = parseFileF.content(filterN);
        System.out.println(itogo2);
        parseFileSave.saveContent(string);
    }
}
