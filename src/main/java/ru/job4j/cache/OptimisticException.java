package ru.job4j.cache;

/**
 * 1. Неблокирующий кеш [#4741]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading
 * Топик : 3.1.5. Non Blocking Algorithm
 * В кеше же нужно перед обновлением данных проверить поле version.
 * Если version у модели и в кеше одинаковы, то можно обновить.
 * Если нет, то выбросить OptimisticException.
 * Перед обновлением данных необходимо проверять текущую версию и ту что обновляем
 * и увеличивать на единицу каждый раз, когда произошло обновление.
 * Если версии не равны -  кидать исключение.
 * часть 3
 * @author SlartiBartFast-art
 * @since 09.09.2021
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
