package com.kidd.demos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

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

    public Person(){}

    public Person(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
