package com.kidd.demos.activemq;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @author Kidd
 */
public class EmbeddedBrokerTest {

    @Test
    public void test() throws Exception{
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("amq/broker.spring.xml");
        Thread.sleep(1000000);
    }
}
