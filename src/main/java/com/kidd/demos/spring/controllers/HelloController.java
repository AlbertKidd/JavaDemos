package com.kidd.demos.spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kidd
 *         CreateTime 2017/12/1.
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello world!";
    }
}
