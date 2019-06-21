package com.kidd.demos.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author Kidd
 */
@Data
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String lastName;
}
