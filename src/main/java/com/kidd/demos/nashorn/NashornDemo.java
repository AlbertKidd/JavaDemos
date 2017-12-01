package com.kidd.demos.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornDemo {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine nashorn = manager.getEngineByName("nashorn");
        try {
            ScriptObjectMirror objectMirror = (ScriptObjectMirror) nashorn.eval("load('src/main/resources/nashorn/js1.js')");
            Object plus = objectMirror.call(null, "plus");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
