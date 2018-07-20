package com.kidd.demos.webservice.server;

import com.kidd.demos.model.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * @author Kidd
 */
@WebService
public interface IWebServiceServer {

    public String queryBytes(byte[] param) throws Exception;

    public String queryString(String param);

    public Person queryObject(Person person);

    public List<Person> queryList(List<Person> personList);

    public String[] queryStringArray(String[] array);

    public Person[] queryObjectArray(Person[] persons);

    public String queryExclude();
}
