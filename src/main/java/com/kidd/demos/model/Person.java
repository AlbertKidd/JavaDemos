package com.kidd.demos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kidd
 */
@Data
public class Person implements Serializable {

    @Id
    private String id;

    private int age;

    private String name;

    private String address;

    private Date birthday;

    public Person(){}

    public Person(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
