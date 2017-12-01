package com.kidd.demos.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NashornDemoTest {

    private ScriptEngine nashorn;

    @Before
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
        String path = "/bundle/srv/combo/asyncErrorCallback.srv";
        InputStream inputStream = getClass().getResourceAsStream(path);
        String s = IOUtils.toString(inputStream, "utf-8");
        InputStream inputStream1 = IOUtils.toInputStream("//@ resourceURL=classpath:" + path + "\\r\\n" + s, "utf-8");
        Object eval = nashorn.eval(new InputStreamReader(inputStream1));
    }
}
