package com.bjtu.redis.tool;

import java.io.IOException;
import java.util.ArrayList;

public class RunAction {
    //读/写counter的名称
    ArrayList<String> readcounters;
    ArrayList<String> writecounters;
    //读/写counter的数量
    int readsize;
    int writesize;
    public RunAction(ActionJsonHelper jh){
        readcounters=jh.getReadcounter();
        writecounters=jh.getWritecounter();
        readsize=readcounters.size();
        writesize=writecounters.size();
    }

    public void run(){
        for(String readcounter:readcounters){
            try {
                CounterJsonHelper cjh = new CounterJsonHelper(readcounter);
                System.out.println("key: "+cjh.getKey());
                System.out.println("valueField: "+cjh.getValueField());
            }catch (Exception e){
                System.out.println("Here comes some exception!");
            }
        }
    }
}
