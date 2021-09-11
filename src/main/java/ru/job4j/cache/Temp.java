package ru.job4j.cache;

import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Joan");

        map.computeIfPresent("name", (key, value) -> key + ", " + value);

        System.out.println(map.get("name")); //output: name, Joan

        Map<String, String> map1 = new HashMap<>();

        map1.computeIfPresent("awesome key", (key, value) -> key + ", " + value);

        System.out.println(map1.get("awesome key")); //output: null

      //  А вот тут, раз такого ключа нет, то и объединение не происходило.

    }
}
