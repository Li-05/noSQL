package com.bjtu.redis.tool;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class CounterJsonHelper {
    public String getCountername() {
        return countername;
    }

    public String getKey() {
        return key;
    }

    public String getValueField() {
        return valueField;
    }

    public String getType() {
        return type;
    }

    private String countername;
    private String key;
    private String valueField;
    private String type;


    public CounterJsonHelper(String countername) throws IOException {
        this.countername=countername;
        ClassLoader loader= FileUtil.class.getClassLoader();
        InputStream stream=loader.getResourceAsStream("counters.json");
        assert stream != null;
        String text = IOUtils.toString(stream,"utf8");
        JSONObject jsonObject = JSONObject.parseObject(text);
        JSONArray array = jsonObject.getJSONArray("counters");

        for (int i = 0; i < array.size(); i++){
            JSONObject jo = array.getJSONObject(i);
            String an = jo.getString("countername");
            if(an.equals(countername)){
                this.type=jo.getString("type");
                this.key=jo.getString("keyfields");
                this.valueField=jo.getString("valuefields");
                break;
            }
        }

    }

}
