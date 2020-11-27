package com.kidd.demos.java.util.map;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Kidd
 */
public class HashMapTest {

    @Test
    public void testConcurrentRead() throws Exception{
        Map<String, Object> personMap = generatePersonMap();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            for (Map.Entry<String, Object> entry : personMap.entrySet()) {
                System.out.printf("thread-1: key-%s\n", entry.getKey());
            }
        });
        executorService.submit(() -> {
            for (Map.Entry<String, Object> entry : personMap.entrySet()) {
                System.out.printf("thread-2: key-%s\n", entry.getKey());
            }
        });
    }

    private Map<String, Object> generatePersonMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Kidd");
        map.put("age", 33);
        map.put("city", "Xi'an");
        map.put("job", "Dev");
        return map;
    }
}
