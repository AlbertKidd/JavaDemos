package com.kidd.demos.json.gson;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        JsonArray jsonArray = new JsonArray();

        Double d = 1.22;
        if (d == d.longValue()){
            System.out.println();
        }

    }
}
