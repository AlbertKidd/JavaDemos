package com.kidd.demos.spring;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MethodInterceptorTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("MethodInterceptorContext.xml");
        InitBean initBean = (InitBean)context.getBean("initBean");
        initBean.show("show something...");
    }
}
