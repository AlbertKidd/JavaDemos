package com.kidd.demos.spring;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Log4j2
public class InitBean implements InitializingBean {

    public void show(String s){
        log.info(s);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("this is a initializing bean");
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("common/springContext.xml");
        InitBean initBean = (InitBean)context.getBean("initBean");
        initBean.show("show something...");
    }
}
