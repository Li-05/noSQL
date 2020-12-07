package com.bjtu.redis;

import com.bjtu.redis.actions.Action;
import com.bjtu.redis.counters.Counter;
import com.bjtu.redis.jedis.JedisUtil;
import com.bjtu.redis.jsonhelpers.ActionJsonHelper;
import com.bjtu.redis.jsonhelpers.CounterJsonHelper;

import java.sql.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
                    System.out.print("begin time>>");
                    Scanner ms = new Scanner(System.in);
                    String begin = ms.nextLine();
                    System.out.print("end time>>");
                    Scanner ms2 = new Scanner(System.in);
                    String end = ms2.nextLine();

                    try {
                        Date from = format.parse(begin);
                        Date to = format.parse(end);

                        //得到time-valueField的哈希表
                        Map<String,String> map = JedisUtil.getHashMap(counter.key);
                        Set<String> keys = map.keySet();
                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        int ret = 0;
                        for(String key:keys){
                            Date it = format2.parse(key);
                            if(it.after(from)&&it.before(to)){
                                String value = map.get(key);
                                ret+=Integer.parseInt(value);
                            }
                        }
                        System.out.println("本时间段count一共增加了"+ret);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    break;
                case "list":
                    List<String> log = JedisUtil.getArray(counter.key);
                    for(String s:log){
                        System.out.println(s);
                    }
                    break;
                case "str":
                    System.out.println(JedisUtil.getValueNum(counter.key));
                    break;
                default:
                    System.out.println(counter);
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
                    //时间戳
                    //存储结构：keyField-time-valueField
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    JedisUtil.setHashPush(counter.key,timestamp.toString(),counter.valueField+"");
                    break;
                case "list":
                    String time = new Timestamp(System.currentTimeMillis()).toString();
                    int value = counter.valueField;
                    String write = "The counter increase "+value+" at "+time;
                    JedisUtil.writeList(counter.key,write);
                    break;
                default:
                    System.out.println(counter);
                    break;
            }
        }
    }


}
