package ru.job4j.ref;

/**
 * 4. Thread без общих ресурсов [#267919]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading Топик : 3.1.2. Общие ресурсы
 */
public class User {
    private int id;
    private String name;

    public static User of(String name) {
        User user = new User();
        user.name = name;
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
