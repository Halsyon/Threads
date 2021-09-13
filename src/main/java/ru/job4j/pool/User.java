package ru.job4j.pool;

/**
 * 2. ExecutorService рассылка почты. [#63097]
 *  Уровень : 3. Мидл Категория : 3.1. Multithreading
 *  Топик : 3.1.6. Пулы
 *  часть 1
 *  Модель User описывают поля username, email.
 *  @author SlartiBartFast-art
 *  @since 13.09.2021
 */
public class User {
    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
