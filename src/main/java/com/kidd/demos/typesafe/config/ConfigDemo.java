package com.kidd.demos.typesafe.config;

import com.google.gson.Gson;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.LinkedHashMap;

/**
 * @author Kidd
 */
public class ConfigDemo {

    @AllArgsConstructor
    static class Entity{
        private String name;
        private int age;
    }

    public static void main(String[] args) {
        ArrayListValuedHashMap<String, Object> map = new ArrayListValuedHashMap<>();
        map.put("b", 0);
        map.put("c", 0);
        map.put("a", 0);
        String s = new Gson().toJson(map);
        Config config = ConfigFactory.parseString(s);
    }
}
