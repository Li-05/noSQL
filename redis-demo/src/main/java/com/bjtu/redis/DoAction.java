package com.bjtu.redis;

import com.bjtu.redis.actions.Action;
import com.bjtu.redis.counters.Counter;
import com.bjtu.redis.jedis.JedisUtil;
import com.bjtu.redis.jsonhelpers.ActionJsonHelper;
import com.bjtu.redis.jsonhelpers.CounterJsonHelper;

import java.util.ArrayList;

public class DoAction {

    Action action;
    private final ArrayList<String> readCounter;
    private final ArrayList<String> writeCounter;

    private final ArrayList<Counter> readCounterObj;
    private final ArrayList<Counter> writeCounterObj;

    public DoAction(ActionJsonHelper jsh){
        this.action=jsh.getAction();
        readCounter=jsh.getReadCounter();
        writeCounter=jsh.getWriteCounter();
        readCounterObj = new ArrayList<Counter>();
        writeCounterObj = new ArrayList<Counter>();
        initCounters();
    }

    private void initCounters(){
        for(String readName:readCounter){
            Counter counter = new Counter(readName);
            new CounterJsonHelper(counter);
            readCounterObj.add(counter);
        }
        for(String writeName:writeCounter){
            Counter counter = new Counter(writeName);
            new CounterJsonHelper(counter);
            writeCounterObj.add(counter);
        }
    }

    /*
    根据Counter数组进行操作
     */
    public void Do(){
        //读所有的read counter
        for(Counter counter : readCounterObj){
            switch (counter.type){
                case "num":
                    System.out.println("The Value Of "+counter.key+" Is "+JedisUtil.getValueNum(counter.key));
                    break;
                case "hash":
                    //System.out.println("暂未实现hash读取");
                    break;
                default:
                    break;
            }
        }
        //写所有的write counter
        for(Counter counter : writeCounterObj){
            switch (counter.type){
                case "num":
                    JedisUtil.setIncrNum(counter.key,counter.valueField);
                    break;
                case "hash":
                    //System.out.println("暂未实现hash写入");
                    break;
                default:
                    break;
            }
        }
    }


}
