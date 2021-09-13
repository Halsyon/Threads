package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2. ExecutorService рассылка почты. [#63097]
 * Уровень : 3. Мидл Категория : 3.1. Multithreading
 * Топик : 3.1.6. Пулы
 * часть 2
 * Реализовать сервис для рассылки почты. Создайте класс EmailNotification.
 * пул = Executors.newFixedThreadPool (размер пула);
 * В классе будет метод emailTo(User user) -
 * Так же добавьте метод close() - он должен закрыть pool.
 * То есть в классе EmailNotification должно быть поле pool,
 * которые используется в emailTo и close().
 * В классе будет метод emailTo(User user) -
 * он должен через ExecutorService отправлять почту.
 * Метод emailTo должен брать данные пользователя и подставлять в шаблон
 * subject = Notification {username} to email {email}.
 * body = Add a new event to {username}
 *
 * @author SlartiBartFast-art
 * @since 13.09.2021
 */
public class EmailNotification {
    final private ExecutorService threadPool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    /**
     * Method takes data User Object and substitutes them in the template,
     * sends mail via ExecutorService
     * Метод берет данные пользователя User подставляет их в шаблон,
     * через ExecutorService отправляет почту
     *
     * @param user User object
     */
    public void emailTo(User user) {
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = "Notification" + user.getUsername() + " to email " + user.getEmail();
                String body = "Add a new event to " + user.getUsername();
                send(user.getUsername(), body, user.getEmail());
            }
        });
    }

    /**
     * Создайте метод public void send(String subject, String body, String email)
     * - он должен быть пустой.
     *
     * @param subject
     * @param body
     * @param email
     */
    public void send(String subject, String body, String email) {
        //some code
    }

    /**
     * Initiates an orderly shutdown in which previously submitted tasks are executed,
     * but no new tasks will be accepted.
     * Инициирует упорядоченное завершение работы, при котором ранее отправленные задачи выполняются,
     * но новые задачи не принимаются.
     */
    public void close() {
        threadPool.shutdown();
    }
}
