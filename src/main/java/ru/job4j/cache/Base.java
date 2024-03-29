package ru.job4j.cache;

import java.util.Objects;

/**
 * 1. Неблокирующий кеш [#4741]
 * ID - уникальный идентификатор. В системе будет только один объект с таким ID.
 * version - определяет достоверность версии в кеше. Подробнее ниже.
 * Поле name - это поля бизнес модели. В нашем примере это одно поле name.
 * Это поле имеет get set методы.
 * часть 1
  * @since 09.09.2021
 */
public class Base {
    private final int id;
    private final int version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version=" + version
                + ", name='" + name + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && version == base.version && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }
}
