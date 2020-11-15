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
        return "当前直播间人数:"+JedisUtil.getCount("count")
                +"<br/>Hello " + name
                + "! <br/>这是您第 "
                + JedisUtil.getCount(name+"count")+" 次进入本直播间";
    }

    //http://localhost:8080/out?name=ljc
    @RequestMapping("/out")
    public String runout(String name){
        JedisUtil.runOut(name);

        return  "退出直播间成功 <br/>您退出后直播间人数:"+JedisUtil.getCount("count")
                +"<br/>Bye "+ name+"~";
    }
}