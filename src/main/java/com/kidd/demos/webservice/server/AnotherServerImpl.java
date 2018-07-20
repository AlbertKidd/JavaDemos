package com.kidd.demos.webservice.server;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author Kidd
 */
@WebService(name = "anotherServer", endpointInterface = "com.kidd.demos.webservice.server.IWebServiceServer")
public class AnotherServerImpl extends AbstractServer {

    @Override
    protected String getTag() {
        return " ----------------- from another server";
    }

    public static void main(String[] args) throws Exception{
        Endpoint.publish("http://localhost:5100/webservice", new AnotherServerImpl());
        Thread.sleep(100000000);
    }
}
