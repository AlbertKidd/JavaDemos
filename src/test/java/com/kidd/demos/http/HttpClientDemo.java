package com.kidd.demos.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kidd
 *         CreateTime 2017/11/21.
 */
public class HttpClientDemo {

    @Test
    public void send() throws Exception{
        HttpPost httpPost = new HttpPost("http://127.0.0.1:2000/ebeit/ws/remoting/download.do");
        httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("param", "this is post param"));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
        httpPost.setEntity(urlEncodedFormEntity);

        CloseableHttpClient aDefault = HttpClients.createDefault();
        aDefault.execute(httpPost);
    }
}
