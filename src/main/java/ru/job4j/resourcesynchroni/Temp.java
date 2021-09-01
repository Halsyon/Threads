package ru.job4j.resourcesynchroni;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Temp {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, User> userList = new ConcurrentHashMap<>();
        AtomicInteger id = new AtomicInteger();
        UserStorage userStorage = new UserStorage();
//        userStorage.add(new User(1, 10500));
//        userStorage.add(new User(2, 500));
//        userStorage.add(new User(3, 1000));
        userList.put(id.incrementAndGet(), new User(1, 10500));
        userList.put(id.incrementAndGet(), new User(2, 1500));
        userList.put(id.incrementAndGet(), new User(3, 10500));
        for (var k : userList.keySet()) {
            System.out.println(k);
        }
        for (var k : userList.entrySet()) {
            System.out.println(k);
        }
    }
}

