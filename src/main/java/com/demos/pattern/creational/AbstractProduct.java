package com.demos.pattern.creational;

import lombok.Data;

/**
 * @author Kidd
 *         CreateTime 2017/10/16.
 */
@Data
public class AbstractProduct{

    private String name, level;

    public AbstractProduct(){}

    public AbstractProduct(String name){
        this.name = name;
    }

}
