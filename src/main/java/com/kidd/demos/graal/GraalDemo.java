package com.kidd.demos.graal;

import org.graalvm.polyglot.Context;

/**
 * @author Kidd
 */
public class GraalDemo {

    public void runJs(){
        Context jsContext = Context.create("js");
        jsContext.eval("js", "console.log('hello');");
    }

    public static void main(String[] args) {
        GraalDemo demo = new GraalDemo();
        demo.runJs();
    }
}
