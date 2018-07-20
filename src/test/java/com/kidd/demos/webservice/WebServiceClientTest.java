package com.kidd.demos.webservice;

import com.kidd.demos.model.Person;
import com.kidd.demos.webservice.server.IWebServiceServer;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kidd
 */
@Slf4j
public class WebServiceClientTest {

    IWebServiceServer servicePort;

    @BeforeTest
    public void setup() throws Exception{
        URL wsdl=new URL("http://localhost:5000/webservice?wsdl");
        QName serviceName=new QName("http://server.webservice.demos.kidd.com/","WebServiceServerService");
        QName portName=new QName("http://server.webservice.demos.kidd.com/","serverImplPort");

        Service service = Service.create(wsdl, serviceName);
        servicePort = service.getPort(portName, IWebServiceServer.class);
    }

    @Test
    public void testQueryString(){
        String kidd = servicePort.queryString("kidd");
        log.info(kidd);
    }

    @Test
    public void testQueryBytes() throws Exception{
        String kidd = servicePort.queryBytes("kidd".getBytes("utf-8"));
        log.info(kidd);
    }

    @Test
    public void testQueryObject(){
        Person person = new Person();
        person.setAddress("Xian Shaanxi");
        Person returnedPerson = servicePort.queryObject(person);
        log.info(returnedPerson.getAddress());
    }

    @Test
    public void testQueryList(){
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Person p = new Person();
            p.setAge(i);
            p.setAddress("Xian Shaanxi" + i);
            list.add(p);
        }
        List<Person> people = servicePort.queryList(list);
        people.forEach(person -> log.info(person.getAddress()));
    }

    @Test
    public void testQueryStringArray(){
        String[] array = new String[]{"kidd", "liufei", "chai"};
        String[] queryStringArray = servicePort.queryStringArray(array);
        for (String s : queryStringArray){
            log.info(s);
        }
    }

    @Test
    public void testQueryObjectArray(){
        List<Person> list = genPersonList(5);
        Person[] queryObjectArray = servicePort.queryObjectArray(list.toArray(new Person[]{}));
        for (Person person : queryObjectArray){
            log.info(person.getAddress());
        }
    }

    @Test
    public void testQueryExclude(){
        servicePort.queryExclude();
    }

    private List<Person> genPersonList(int num){
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < num; i++){
            Person p = new Person();
            p.setAge(i);
            p.setAddress("Xian Shaanxi" + i);
            list.add(p);
        }
        return list;
    }

}
