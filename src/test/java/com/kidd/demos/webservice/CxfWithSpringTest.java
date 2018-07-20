package com.kidd.demos.webservice;

import com.kidd.demos.webservice.server.IWebServiceServer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @author Kidd
 */
public class CxfWithSpringTest {

    @Test
    public void server() throws Exception{
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("webservice/server.xml");
        Thread.sleep(100000000);
    }

    @Test
    public void client(){
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("webservice/client.xml");
        IWebServiceServer client = (IWebServiceServer) applicationContext.getBean("cxfClient");

    }
}
