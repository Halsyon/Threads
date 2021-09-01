package ru.job4j.resourcesynchroni;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAdd() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 10500));
        userStorage.add(new User(2, 500));
        userStorage.add(new User(3, 1000));
        int expected = userStorage.getSize();
        assertThat(expected, Is.is(3));
    }

    @Test
    public void whenUpdate() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 10500));
        userStorage.add(new User(2, 500));
        userStorage.add(new User(3, 1000));
        User user = new User(2, 100500);

        assertThat(userStorage.update(user), Is.is(true));
        System.out.println(userStorage.toString());
    }

    @Test
    public void whenDelete() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 10500));
        userStorage.add(new User(2, 500));
        User user = new User(3, 1000);
        userStorage.add(user);

        assertThat(userStorage.delete(user), Is.is(true));
        System.out.println(userStorage.toString());
    }

    @Test
    public void whenTransfer() {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 1050));
        userStorage.add(new User(2, 500));
        userStorage.add(new User(3, 1000));

        assertThat(userStorage.transfer(1, 2, 50), Is.is(true));
        System.out.println(userStorage.toString());
    }
}