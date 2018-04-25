
package com.kidd.demos.activeMQ;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * @author Kidd
 */
@Log4j2
public class ActiveMQDemo {

    public enum DestType{
        topic, queue
    }

    public static final String CONN_OPEN_WIRE = "tcp://localhost:61616";

    private static ActiveMQConnectionFactory factory;
    private static Connection connection;
    private static Session session;

    /**
     * 连接指定的消息协议地址的topic或queue
     * @param connURL
     * @param destType
     * @param destName
     * @return
     * @throws Exception
     */
    private static Destination connect(String connURL, DestType destType, String destName) throws Exception{
        //broker连接信息，broker各协议端口配置信息位于broker所在目录的conf/activemq.xml中
        factory = new ActiveMQConnectionFactory(null, null, connURL);
        connection = factory.createConnection();
        connection.setClientID("KiddClientId");
        connection.start();
        //创建连接会话，第一个参数为是否使用事务，使用事务时，如果事务回滚，即使已经接受的消息也会再次发送
        //第二个参数控制消息接受，AUTO_ACKNOWLEDGE会自动标记消息接受状态，CLIENT_ACKNOWLEDGE则需收信端调用消息的acknowledge方法签收
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建Destination
        switch (destType){
            case queue:
                return new ActiveMQQueue(destName);
            case topic:
                return new ActiveMQTopic(destName);
            default:
                return null;
        }
    }

    /**
     * 发送文本消息，可指定协议、消息类型、消息目标名称、消息内容
     * @param connURL
     * @param destType
     * @param destName
     * @param msg
     */
    public static void sendMsg(String connURL, DestType destType, String destName, String msg) throws Exception{

        //创建Dest
        Destination dest = connect(connURL, destType, destName);
        //创建Producer
        MessageProducer producer = session.createProducer(dest);
        //消息持久化，若设为NON_PERSISTENT，则当broker关闭时，消息队列将被清空
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //创建文本消息
        TextMessage textMessage = session.createTextMessage(msg);
        producer.send(textMessage);

        connection.close();
    }

    /**
     * 接受文本消息，可指定协议、消息类型、消息目标名称、消息内容
     * @param connURL
     * @param destType
     * @param destName
     */
    public static void receiveMsg(String connURL, DestType destType, String destName) throws Exception{

        //连接Dest
//        Destination dest = connect(connURL, destType, destName);
        Topic dest = (Topic)connect(connURL, destType, destName);

//        MessageConsumer consumer = session.createConsumer(dest);
        MessageConsumer consumer = session.createDurableSubscriber(dest, "KiddSubscriber");
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    String msg = ((TextMessage)message).getText();
                    log.info(msg);
                } catch (JMSException e) {
                    log.error(e);
                }
            }
        });

        Thread.sleep(60 * 60 * 1000);
        connection.close();
    }
}