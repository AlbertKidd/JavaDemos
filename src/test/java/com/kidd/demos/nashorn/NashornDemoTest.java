package com.kidd.demos.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.io.IOUtils;
import org.springframework.web.util.JavaScriptUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class NashornDemoTest {

    private ScriptEngine nashorn;

    @BeforeTest
    public void setup(){
        ScriptEngineManager manager = new ScriptEngineManager();
        nashorn = manager.getEngineByName("nashorn");

    }

    @Test
    public void test1() throws Exception{
        ScriptObjectMirror objectMirror = (ScriptObjectMirror) nashorn.eval("load('src/main/resources/nashorn/js1.js')");
        Object plus = objectMirror.call(null, "plus");
    }

    @Test
    public void test2() throws Exception{
        String path = "/nashorn/js1.js";
        InputStream inputStream = getClass().getResourceAsStream(path);
        String s = IOUtils.toString(inputStream, "utf-8");
        InputStream inputStream1 = IOUtils.toInputStream("//@ resourceURL=classpath:" + path + "\\r\\n" + s, "utf-8");
        Object eval = nashorn.eval(new InputStreamReader(inputStream1));
    }

    @Test
    public void test3() throws Exception{
        InputStream inputStream = NashornDemo.class.getResourceAsStream("/js2.js");
        Reader reader = new InputStreamReader(inputStream);
        ScriptObjectMirror objectMirror = (ScriptObjectMirror) nashorn.eval(reader);
        Object plus = objectMirror.call(null, "plus");
    }

    @Test
    public void test4() throws Exception {
        String script1 = "(function(){return JSON.parse('{\"a\": null}')})()";
        Object result1 = nashorn.eval(script1);

        String json = "{\"extraChanges\":{\"qixieziliao\":[{\"value\":null,\"id\":\"3fa70e3f-21cb-481b-a778-db7fa8c583c7\",\"name\":\"attr2\",\"tableName\":\"yfzx_specialty\",\"tableKey\":1,\"title\":\"长相\",\"editor\":\"{\\\"xtype\\\":\\\"optionlist\\\",\\\"name\\\":\\\"attr2\\\",\\\"fieldLabel\\\":\\\"长相\\\",\\\"servicePath\\\":\\\"/democore/func/combobox/basicOptionList/accountCombo.option\\\",\\\"queryKey\\\":\\\"_default\\\",\\\"valueField\\\":\\\"id\\\",\\\"displayField\\\":\\\"text\\\"}\"}]},\"dataSetChange\":{\"flag\":\"Modified\",\"id\":1},\"mode\":\"single\"}";
        String script2 = String.format("(function(){return JSON.parse('%s');})()", JavaScriptUtils.javaScriptEscape(json));
        Object result2 = nashorn.eval(script2);


        System.out.println(result2);
    }


}
