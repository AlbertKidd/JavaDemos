package com.kidd.demos.pattern.creational;

/**
 * @author Kidd
 *         CreateTime 2017/10/16.
 */
public class SimpleFactory {

    public static AbstractProduct createProduct(String productType){
        switch (productType){
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return new AbstractProduct();
        }
    }
}

