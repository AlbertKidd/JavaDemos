package com.kidd.demos.spring.web;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * @author Kidd
 */
public class RestTemplateTest {

    private static final String URL_BAIDU = "http://www.baidu.com";

    @Test
    public void test() throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(URL_BAIDU);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getForEntity(uri, ResponseEntity.class);
    }
}
