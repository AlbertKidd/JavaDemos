package com.kidd.demos.java.util;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * @author Kidd
 */
@Log4j2
public class HashMapTest {

    @Test
    public void testNullKey(){
        HashMap<String, Object> map = new HashMap<>();
        map.put(null, "something");
        boolean result = map.containsKey(null);
        log.info("result of HashMap use null key is {}", result);
    }

    @Test
    public void testNullValue(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("something", null);
        boolean result = map.containsKey("something");
        log.info("result of HashMap use null value is {}", result);
    }
}
