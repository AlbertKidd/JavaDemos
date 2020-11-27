package com.kidd.demos.activemq;

import com.kidd.demos.amq.ActiveMQDemo;
import org.apache.activemq.advisory.AdvisorySupport;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.Test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * @author Kidd
 * CreateTime 2018/1/18.
 */
public class ActiveMQDemoTest {

    @Test
    public void testSendQueue() throws Exception {
        for (;;){
            ActiveMQDemo.sendMsg(ActiveMQDemo.CONN_OPEN_WIRE, ActiveMQDemo.DestType.queue, "KiddQueue", "QueueMsg");
            Thread.sleep(5000);
        }
    }

    @Test
    public void testReceiveQueue() throws Exception {
        ActiveMQDemo.receiveMsg(ActiveMQDemo.CONN_OPEN_WIRE, ActiveMQDemo.DestType.queue, "KiddQueue");

    }

    @Test
    public void testSendTopic() throws Exception {
        ActiveMQDemo.sendMsg(ActiveMQDemo.CONN_OPEN_WIRE, ActiveMQDemo.DestType.topic, "KiddTopic", "TopicMsg");
    }

    @Test
    public void testReceiveTopic() throws Exception {
        ActiveMQDemo.receiveMsg(ActiveMQDemo.CONN_OPEN_WIRE, ActiveMQDemo.DestType.topic, "KiddTopic");

    }

    @Test
    public void testSpringSend() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("amq/client.spring.xml");
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        Topic kiddTopic = (Topic) context.getBean("kiddTopic");
        // send to bean destination
        jmsTemplate.convertAndSend(kiddTopic, "hello Kidd");
        // send to String destination
        // jmsTemplate.convertAndSend("topicA", "hello Kidd");
    }

    @Test
    public void testJmsTemplateReceive() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("amq/client.spring.xml");
        Topic kiddTopic = (Topic) context.getBean("kiddTopic");
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        for (;;){
            Message receive = jmsTemplate.receive(kiddTopic);
            TextMessage textMessage = (TextMessage)receive;
            System.out.println("收到——————————————" + textMessage.getText());
            Thread.sleep(3000);
        }
    }

    @Test
    public void testAdvisory() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("amq/client.spring.xml");
        Topic kiddTopic = (Topic) context.getBean("kiddTopic");
        ActiveMQTopic consumerAdvisoryTopic = AdvisorySupport.getConsumerAdvisoryTopic(kiddTopic);

        ConnectionFactory connectionFactory = context.getBean(ConnectionFactory.class);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(consumerAdvisoryTopic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message);
            }
        });
        Thread.sleep(100000000000L);
    }

    public void testSendAndReceive(){

    }

}