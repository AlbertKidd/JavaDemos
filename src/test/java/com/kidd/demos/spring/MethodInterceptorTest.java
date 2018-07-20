package com.kidd.demos.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

public class MethodInterceptorTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("common/MethodInterceptorContext.xml");
        InitBean initBean = (InitBean)context.getBean("initBean");
        initBean.show("show something...");
    }
}
