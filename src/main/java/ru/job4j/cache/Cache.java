package ru.job4j.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. Неблокирующий кеш [#4741]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading
 * Топик : 3.1.5. Non Blocking Algorithm
 * CAS методами потокобезопасной коллекции ConcurrentHashMap.
 * Кеш - это элемент программы позволяющий увеличить скорость
 * работы за счет хранения данных в памяти.
 * В кеше должна быть возможность проверять актуальность данных.
 * Для этого в модели данных(class Base) используется поле int version.
 * ConcurrentHashMap потокобезопасна, но последовательные вызовы методов нет.
 * Это не атомарные операции.
 * Для решения этих задач нужно использовать CAS методы.
 * Метод putIfAbsent выполняет методы сравнения и вставки, только делает это атомарно.
 * Метод computeIfPresent принимает функцию BiFunction и позволяет атомарно обновить нужную ячейку.
 * В общем случае целью этого метода является обновление существующего значения в любой форме.
 * a) Реализуйте методы update, delete. *
 * b) Напишите модульные тесты. Они будут не многопоточные, а последовательные.
 *
 * @author SlartiBartFast-art
 * @since 10.09.2021
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public Map<Integer, Base> getMemory() {
        return memory;
    }

    /**
     * The add(Object object) method of class Cache is used to
     * method ConcurrentHashMap class to putIfAbsent
     *
     * @param model Object class Base
     * @return true if action was successful or false
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * version - Это поле должно увеличиваться на единицу каждый раз, когда модель изменили,
     * то есть вызвали метод update.
     * Даже если все поля остались не измененными поле version должно увеличиться на 1
     * default V computeIfPresent(K key,
     * BiFunction<? super K, ? super V, ? extends V> remappingFunction) {}
     * FunctionalInterface public interface BiFunction<T, U, R> {
     * * Applies this function to the given arguments.
     * * @param t the first function argument
     * * @param u the second function argument
     * * @return the function result
     *
     * @param model Base class object
     * @return true or false
     */
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (key, val) -> {
            if (val.getVersion() != model.getVersion()) {
                throw new OptimisticException("version is not equals");
            }
            return new Base(key, val.getVersion() + 1);
        }) != null;
    }

    /**
     * The remove(Object key) method of class ConcurrentHashmap in Java is used to remove
     * the mapping from the map.
     * If the key does not exist in the map, then this function does nothing.
     *
     * @param model Object class Base
     */
    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public int size() {
        return memory.size();
    }

    public static void main(String[] args) {
        Map<Integer, Base> map = new HashMap<>();
        Base base1 = new Base(1, 0);
        map.put(base1.getId(), base1);

        Base user1 = map.get(1);
        user1.setName("User 1");

        Base user2 = map.get(1);
        user1.setName("User 2");

        map.put(user1.getId(), user1);
        map.put(user2.getId(), user2);

        System.out.println(map);
    }

    @Override
    public String toString() {
        return "Cache{"
                + "memory=" + memory
                + '}';
    }
}

