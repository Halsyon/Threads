package ru.job4j.ref;


import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4. Thread без общих ресурсов [#267919]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.2. Общие ресурсы
 * избавление от общих ресурсов.
 * Программист решил расширить класс UserCache и добавил в него новый метод findAll.
 * Исправьте ошибку в этом коде.
 */
@NotThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    //ConcurrentHashMap values() - используется для создания коллекции из значений карты.
// Он в основном возвращает представление коллекции значений в ConcurrentHashMap.
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        var ret = users.values();
        for (User user : ret) {
            var u = User.of(user.getName());
            u.setId(user.getId());
            userList.add(u);
        }
        return userList;
    }
}
