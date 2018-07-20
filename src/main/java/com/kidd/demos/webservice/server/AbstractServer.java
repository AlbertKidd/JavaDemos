package com.kidd.demos.webservice.server;

import com.kidd.demos.model.Person;
import lombok.extern.slf4j.Slf4j;

import javax.jws.WebService;
import java.util.List;

/**
 * @author Kidd
 */
@Slf4j
@WebService
public abstract class AbstractServer implements IWebServiceServer {

    abstract protected String getTag();

    @Override
    public String queryBytes(byte[] param) throws Exception{
        log.info("query Bytes start");
        return new String(param, "utf-8");
    }

    @Override
    public String queryString(String param){
        log.info("query String start");
        return param + getTag();
    }

    @Override
    public Person queryObject(Person person){
        log.info("query Object start");
        person.setAddress(person.getAddress() + getTag());
        return person;
    }

    @Override
    public List<Person> queryList(List<Person> personList){
        log.info("query List start");
        personList.forEach((person -> {
            person.setAddress(person.getAddress() + getTag());
        }));
        return personList;
    }

    @Override
    public String[] queryStringArray(String[] array){
        log.info("query String Array start");
        String[] strs = new String[array.length];
        for (int i = 0; i < array.length; i++){
            strs[i] = array[i] + getTag();
        }
        return strs;
    }

    @Override
    public Person[] queryObjectArray(Person[] persons){
        log.info("query Object Array start");
        for (Person person : persons){
            person.setAddress(person.getAddress() + getTag());
        }
        return persons;
    }

    @Override
    public String queryExclude(){
        log.info("never be logged");
        return "never to return";
    }
}
