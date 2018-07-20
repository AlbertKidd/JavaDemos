package com.kidd.demos.webservice;

import com.kidd.demos.webservice.server.IWebServiceServer;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * @author Kidd
 */
public class AnotherServiceTest {

    @Test
    public void testAnotherClient() throws Exception{
        URL wsdl=new URL("http://localhost:5100/webservice?wsdl");
        QName serviceName=new QName("http://server.webservice.demos.kidd.com/","AnotherServerService");
        QName portName=new QName("http://server.webservice.demos.kidd.com/","anotherServerPort");

        Service service=Service.create(wsdl, serviceName);
        IWebServiceServer servicePort = service.getPort(portName, IWebServiceServer.class);
        String kidd = servicePort.queryString("kidd");
        System.out.println(kidd);
    }
}
