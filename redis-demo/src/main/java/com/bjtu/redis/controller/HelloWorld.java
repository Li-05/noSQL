package com.bjtu.redis.controller;

import com.bjtu.redis.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bjtu.redis.JedisInstance;

@RestController
public class HelloWorld {

    //http://localhost:8080/hello?name=World
    @RequestMapping("/hello")
    public String Hello(String name) {
        JedisUtil.setIncr("count",10000);
        return "Hello " + name;
    }

}