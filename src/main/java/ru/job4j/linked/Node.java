package ru.job4j.linked;

/**
 * 3. Immutable объекты [#267918]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.2. Общие ресурсы
 * Класс описывает узел односвязной ноды
 * @param <T> параметризован
 *     Ниже приведен код не потокобезопасного класса, описывающего узел односвязного списка.
 *     Сделайте этот класс Immutable.
 */
public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}
