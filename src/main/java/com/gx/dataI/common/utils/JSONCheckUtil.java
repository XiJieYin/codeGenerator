package com.gx.dataI.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JSONCheckUtil {

    private final static String BASE_PATH = "/JSONCheckRules/";

    public static String checkKey(JSONObject json,String conf){
        InputStream inStream = FastDFSUtil.class.getResourceAsStream(BASE_PATH+ conf +".properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
            for (int i=0;i<5;i++){
                String rule = prop.getProperty("level"+i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
