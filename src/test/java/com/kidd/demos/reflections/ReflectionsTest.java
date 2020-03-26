package com.kidd.demos.reflections;

import com.google.gson.TypeAdapter;
import org.reflections.Reflections;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * @author Kidd
 */
public class ReflectionsTest {

    @Test
    public void test(){
        long start = System.currentTimeMillis();
        Reflections reflections = new Reflections("com.kidd.demos");
        Set<Class<? extends TypeAdapter>> subTypesOf = reflections.getSubTypesOf(TypeAdapter.class);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        for (Class<?> cls : subTypesOf){
            System.out.println(cls.getName());
        }
    }
}
