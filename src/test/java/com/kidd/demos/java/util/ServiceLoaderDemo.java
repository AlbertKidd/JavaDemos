package com.kidd.demos.java.util;

import com.kidd.demos.model.Person;
import org.testng.annotations.Test;

import java.util.ServiceLoader;

/**
 * @author Kidd
 */
public class ServiceLoaderDemo {

    @Test
    public void test(){
        ServiceLoader<Person> serviceLoader = ServiceLoader.load(Person.class);
        for (Person person : serviceLoader){
            Class<? extends Person> cls = person.getClass();
            System.out.println(cls.getName());
        }
    }
}
