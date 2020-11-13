package com.bjtu.redis.controller;

import com.bjtu.redis.tool.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //http://localhost:8080/in?name=ljc
    @RequestMapping("/in")
    public String comein(String name) {
        JedisUtil.comeIn(name);
        System.out.println("count = "+ JedisUtil.getCount());
        return "Hello " + name;
    }

    //http://localhost:8080/out?name=ljc
    @RequestMapping("/out")
    public String runout(String name){
        JedisUtil.runOut(name);
        System.out.println("count = "+ JedisUtil.getCount());
        return "Bye "+ name;
    }
}