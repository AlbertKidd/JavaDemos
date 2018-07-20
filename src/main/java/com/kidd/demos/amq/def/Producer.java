package com.kidd.demos.amq.def;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Kidd on 2017/7/13.
 */
@Log4j2
public class Producer {

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //发送的消息数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        Connection connection = null;
        Session session;
        Destination destination;
        MessageProducer messageProducer;

        try{
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("KiddQueue");
            messageProducer = session.createProducer(destination);
            sendMessage(session, messageProducer);
            session.commit();
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

    }

    private static void sendMessage(Session session, MessageProducer messageProducer) throws Exception{
        for (int i = 0; i < SENDNUM; i++){
            TextMessage message = session.createTextMessage("消息" + i);
            log.info("logging send message" + i);
            messageProducer.send(message);
        }
    }
}
