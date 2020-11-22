package com.bjtu.redis.controller;

import com.bjtu.redis.tool.ActionJsonHelper;
import com.bjtu.redis.tool.RunAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    //http://localhost:8080/comeIn?name=ljc
    @RequestMapping("/comeIn")
    public String comein(String name) throws IOException {
        ActionJsonHelper a = new ActionJsonHelper("comeIn");
        new RunAction(a).run();
        return name+"进入直播间";
    }

    //http://localhost:8080/comeOut?name=ljc
    @RequestMapping("/comeOut")
    public String runout(String name) throws IOException {
        ActionJsonHelper a = new ActionJsonHelper("comeOut");
        new RunAction(a).run();
        return  name+"退出直播间";
    }
}