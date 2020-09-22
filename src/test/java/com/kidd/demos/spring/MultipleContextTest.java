package com.kidd.demos.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @author Kidd
 */
public class MultipleContextTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("/spring/test1.spring.xml", "/spring/test2.spring.xml");
        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("/spring/test2.spring.xml", "/spring/test1.spring.xml");
    }
}
