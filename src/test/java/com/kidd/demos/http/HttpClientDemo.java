package com.kidd.demos.http;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kidd
 *         CreateTime 2017/11/21.
 */
public class HttpClientDemo {

    @Test
    public void test() throws Exception{
        HttpPost httpPost = new HttpPost("http://192.168.1.21:2272/tpl/ws/esb/mescm_skupack_download");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("json", getJsonParam()));

        httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
        CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
        InputStream content = httpResponse.getEntity().getContent();
        String result = IOUtils.toString(content, "utf-8");
        System.out.println(result);
    }

    private String getJsonParam(){
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "koala");
        return new Gson().toJson(map);
    }

}
