package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2. ExecutorService рассылка почты. 
 * Топик : 3.1.6. Пулы
 * часть 2
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
