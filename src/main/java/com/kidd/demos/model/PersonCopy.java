package com.kidd.demos.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 与Person完全一致的类
 * @author Kidd
 */
@Data
public class PersonCopy {

    @Id
    private String id;

    private int age;

    private String name;

    private String address;
}
