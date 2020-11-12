package com.bjtu.redis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorld {

    //http://localhost:8080/hello?name=World
    @RequestMapping("/hello")
    public String Hello(String name) {
        return "Hello " + name;
    }

}