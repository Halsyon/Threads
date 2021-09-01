package ru.job4j.io;

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
public interface Parse {

   public String content(Predicate<Character> filter);
}
