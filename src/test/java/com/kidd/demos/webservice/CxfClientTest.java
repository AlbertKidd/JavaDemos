package com.kidd.demos.webservice;

import com.kidd.demos.model.Person;
import lombok.extern.log4j.Log4j2;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kidd
 */
@Log4j2
public class CxfClientTest {

    private Client client;

    @BeforeTest
    public void setup() throws Exception{
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        client = factory.createClient("http://localhost:5000/webservice?wsdl");
    }

    @Test
    public void testQueryString() throws Exception{
        Object[] invoke = client.invoke("queryString", "Kidd");
        log.info((String) invoke[0]);
    }

    @Test
    public void testQueryBytes() throws Exception{
        Object[] invoke = client.invoke("queryBytes", "kidd".getBytes("utf-8"));
        log.info((String) invoke[0]);
    }

    @Test
    public void testQueryObject() throws Exception{
        Person person = new Person();
        person.setAddress("Xian Shaanxi");
        Object[] invoke = client.invoke("queryObject", person);
        log.info(((Person) invoke[0]).getAddress());
    }

    @Test
    public void testQueryList() throws Exception{

        Object[] invoke = client.invoke("queryList", genPersonList(5));
        for (Object o : invoke){
            Person person = (Person) o;
            log.info(person.getAddress());
        }
    }

    @Test
    public void testQueryStringArray() throws Exception{
        String[] array = new String[]{"kidd", "liufei", "chai"};
        List<String> list = new ArrayList<>();
        list.add("kidd");
        list.add("liufei");
        Object[] invoke = client.invoke("queryStringArray", list);
        List<String> strings = (List<String>) invoke[0];
        for (String s : strings){
            log.info(s);
        }
    }

    @Test
    public void testQueryObjectArray() throws Exception{
        List<Person> list = genPersonList(5);
        Object[] invoke = client.invoke("queryObjectArray", list.toArray(new Person[]{}));
        for (Object o : invoke){
            Person person = (Person) o;
            log.info(person.getAddress());
        }
    }

    @Test
    public void testQueryExclude() throws Exception{
        Object[] invoke = client.invoke("queryExclude", "Kidd");
        log.info((String) invoke[0]);

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
