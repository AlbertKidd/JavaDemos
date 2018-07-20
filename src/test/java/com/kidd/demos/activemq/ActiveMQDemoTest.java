package com.kidd.demos.activemq;

import com.kidd.demos.amq.ActiveMQDemo;
import com.kidd.demos.amq.spring.embedded.Broker;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.Test;

import javax.jms.Message;
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

}