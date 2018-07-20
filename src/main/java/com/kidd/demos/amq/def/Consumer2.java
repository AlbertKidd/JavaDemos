package com.kidd.demos.amq.def;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

/**
 * Created by Kidd on 2017/7/13.
 */
@Log4j2
public class Consumer2 {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;

        try {
            connection = connectionFactory.createConnection();
            connection.setClientID("client2");
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            destination = session.createQueue("KiddQueue");
//            destination = session.createTopic("KiddTopic");
            Topic topic = session.createTopic("KiddTopic");
            TopicSubscriber subscriber = session.createDurableSubscriber(topic, "client2");
//            messageConsumer = session.createConsumer(destination);
            for (;;){
                TextMessage textMessage = (TextMessage) subscriber.receive(2 * 1000);
                if (textMessage != null){
                    log.info("收到消息:" + textMessage.getText());
                }else {
                    break;
                }
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
    }
}
