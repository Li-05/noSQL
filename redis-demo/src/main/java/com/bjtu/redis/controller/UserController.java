package com.bjtu.redis.controller;

import com.bjtu.redis.tool.JedisUtil;
import com.bjtu.redis.tool.JsonHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    //http://localhost:8080/comeIn?name=ljc
    @RequestMapping("/comeIn")
    public String comein(String name) throws IOException {
        //JedisUtil.comeIn(name);
        JsonHelper a = new JsonHelper("comeIn");
        return "当前直播间人数:"+JedisUtil.getCount("count");
    }

    //http://localhost:8080/comeOut?name=ljc
    @RequestMapping("/comeOut")
    public String runout(String name){
        JedisUtil.runOut(name);

        return  "退出直播间成功 <br/>您退出后直播间人数:"+JedisUtil.getCount("count");
    }
}