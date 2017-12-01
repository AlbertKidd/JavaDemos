package com.kidd.demos.json.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author Kidd
 *         CreateTime 2017/11/22.
 */
public class FastJsonDemo {

    @Test
    public void test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aa", null);
        jsonObject.put("bb", "bb");
    }
}
