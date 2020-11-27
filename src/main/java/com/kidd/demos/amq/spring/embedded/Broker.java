package com.kidd.demos.amq.spring.embedded;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

import java.util.Collections;

/**
 * @author Kidd
 */
public class Broker {

    private static final String BROKER_NAME = "kidd_broker";
    private static final String DATA_PATH = "amq/data";
    private static final String KAHADB_DATA_PATH = "amq/kahadb";
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "yidakeji";
    private static final String ADMIN_GROUP = "admin";
    private static final String CONNECTOR_RUI_TCP = "tcp://0.0.0.0:62626?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600";

    public void start() throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setBrokerName(BROKER_NAME);
        brokerService.setUseJmx(true);
        brokerService.setDataDirectory(DATA_PATH);
        brokerService.setTransportConnectorURIs(new String[]{CONNECTOR_RUI_TCP});

        // security
        SimpleAuthenticationPlugin authenticationPlugin = new SimpleAuthenticationPlugin();
        AuthenticationUser user = new AuthenticationUser(ADMIN_USERNAME, ADMIN_PASSWORD, ADMIN_GROUP);
        authenticationPlugin.setUsers(Collections.singletonList(user));
        brokerService.setPlugins(new BrokerPlugin[]{authenticationPlugin});

        // todo setPersistenceAdapter

        // todo system usage

        brokerService.start();
    }

    public static void main(String[] args) throws Exception{
        Broker broker = new Broker();
        broker.start();
        Thread.sleep(10000000);
    }

}
