package com.kidd.demos.json.gson;

import com.google.gson.JsonObject;
import org.junit.Test;

/**
 * @author Kidd
 *         CreateTime 2017/11/22.
 */
public class GsonDemo {

    @Test
    public void test(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("aa", null);
        jsonObject.addProperty("bb", "bb");
    }
}
