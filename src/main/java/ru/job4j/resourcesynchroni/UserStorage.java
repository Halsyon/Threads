package ru.job4j.resourcesynchroni;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Класс хранилища пользователей UserStorage [#1104]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.3. Синхронизация ресурсов
 * В этом задании нужно сделать блокирующий кеш UserStorage для модели User.
 * 1. Создать класс - структуру данных для хранение пользователей UserStorage.
 * 2. В заголовке класса обозначить аннотацию @ThreadSafe
 * из библиотеки  <groupId>net.jcip</groupId>
 * 3. Хранилище должно иметь методы boolean add (User user),
 * boolean update(User user), boolean delete(User user).
 * 4. И особый метод transfer(int fromId, int toId, int amount);
 * 5. Структура данных должна быть потокобезопасная;
 * 6. В структуре User Должны быть поля int id, int amount.
 */
@ThreadSafe
public class UserStorage {

    private final ConcurrentHashMap<Integer, User> userList = new ConcurrentHashMap<>();
    //private final AtomicInteger id = new AtomicInteger();

    /**
     * Method execute adding Object User to userList
     * Returns:
     * the previous value associated with key (in case true), or null if there was no mapping for key.
     *
     * @param user object User
     * @return true or false
     */
    public synchronized boolean add(User user) {
        var k = userList.put(user.getId(), new User(user.getId(), user.getAmount()));
        if (k == null) {
            return true;
        }
        return false;
    }

    /**
     * Method execute update userList values
     *
     * @param user Object which the we want do update
     * @return true if it was successful or false
     */
    public synchronized boolean update(User user) {
        if (userList.get(user.getId()).getId() == user.getId()) {
            userList.put(user.getId(), new User(user.getId(), user.getAmount()));
            return true;
        }
        return false;
    }

    /**
     * Method execute delete Object User from userList ConcurrentHashMap
     * @param user Object
     * @return true if it was successful or false
     */
    public synchronized boolean delete(User user) {
        return userList.remove(user.getId(), user);
    }

    /**
     * Метод переводит деньги от одного клиента к другому
     *
     * @param fromId кличет от которого осущес-ся перевод
     * @param toId   клиет которому осущ-ся перевод
     * @param amount сумма перевода
     * @return true if it was successful or false
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User user = null;
        User user1 = null;
        if (fromId == 0 || toId == 0 || amount == 0) {
            return false;
        }
        user = new User(userList.get(fromId).getId(),
                userList.get(fromId).getAmount() - amount); //from

        user1 = new User(userList.get(toId).getId(),
                userList.get(toId).getAmount() + amount); //to
        update(user);
        update(user1);
        return true;
    }

    /**
     * Method show userList size
     *
     * @return userList size
     */
    public synchronized int getSize() {
        return userList.size();
    }

    @Override
    public String toString() {
        return "UserStorage{"
                + "userList=" + userList
                + '}';
    }
}
