package com.kidd.demos.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Kidd
 */
@Getter @Setter
public class Person implements Serializable {

    private int age;

    private String name;

    private String address;
}
