package com.kidd.demos.webservice.server;

import lombok.extern.log4j.Log4j2;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * @author Kidd
 */
@Log4j2
@WebService
public class ServerImpl extends AbstractServer{

    @Override
    protected String getTag() {
        return " ----------------- from server";
    }

    public static void main(String[] args) throws Exception {
        Endpoint.publish("http://0.0.0.0:5000/webservice", new ServerImpl());
        Thread.sleep(100000000);
    }
}
