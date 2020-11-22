package com.bjtu.redis.tool;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import ch.qos.logback.core.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ActionJsonHelper {

    private final ArrayList<String> readcounter;
    private final ArrayList<String> writecounter;

    public ActionJsonHelper(String action) throws IOException {
        readcounter = new ArrayList<String>();
        writecounter = new ArrayList<String>();

        ClassLoader loader= FileUtil.class.getClassLoader();
        InputStream stream=loader.getResourceAsStream("actions.json");
        assert stream != null;
        String text = IOUtils.toString(stream,"utf8");
        JSONObject jsonObject = JSONObject.parseObject(text);
        JSONArray array = jsonObject.getJSONArray("actions");
        for (int i = 0; i < array.size(); i++){
            JSONObject jo = array.getJSONObject(i);
            String an = jo.getString("actionname");
            if(an.equals(action)){
                JSONObject jo1 = jo.getJSONObject("feature_retrieve");
                JSONObject jo2 = jo.getJSONObject("save_counter");
                JSONArray Ja1= jo1.getJSONArray("counter");
                JSONArray Ja2= jo2.getJSONArray("counter");

                for(int j=0;j<Ja1.size();j++){
                    JSONObject jn = Ja1.getJSONObject(j);
                    readcounter.add(jn.getString("countname"));
                }
                for(int j=0;j<Ja2.size();j++){
                    JSONObject jn = Ja2.getJSONObject(j);
                    writecounter.add(jn.getString("countname"));
                }
                break;
            }
        }
    }

    public ArrayList<String> getReadcounter() {
        return readcounter;
    }

    public ArrayList<String> getWritecounter() {
        return writecounter;
    }
}
