package com.kidd.demos.webservice.server;


import javax.xml.ws.Endpoint;

/**
 * @author Kidd
 */
public class CxfServerImpl extends AbstractServer {
    @Override
    protected String getTag() {
        return " ----------------- from cxf server";
    }

    public static void main(String[] args) throws Exception{
        Endpoint.publish("http://localhost:9990/hello", new CxfServerImpl());
        Thread.sleep(10000000000000000L);
    }

}
