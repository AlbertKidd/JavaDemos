package com.kidd.demos.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.io.IOUtils;
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


}
