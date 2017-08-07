package com.demos.activeMQ.amqp.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.qpid.jms.JmsConnectionFactory;

public class HelloSender {  
  
    public static void main(String[] args) throws JMSException {  
  
//        String connectionURI = "amqp://localhost:5672";
        String connectionURI = "amqp://192.168.2.149:5672";
        ConnectionFactory connectionFactory = new JmsConnectionFactory(connectionURI);
  
        Connection connection = connectionFactory.createConnection();// 创建连接  
  
        Session session = connection.createSession(false,  
                Session.AUTO_ACKNOWLEDGE);// 打开会话  
  
        Destination dest = session.createQueue("demoQueue_anders");// 消息目的地  
  
        MessageProducer producer = session.createProducer(dest);// 消息发送者  
  
        Message message = session.createTextMessage("hello world");// 消息  
  
        producer.send(message);// 发送  
  
        producer.close();// 关闭  
        session.close();  
        connection.close();  
  
    }  
  
}  