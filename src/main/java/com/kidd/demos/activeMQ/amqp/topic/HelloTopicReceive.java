package com.kidd.demos.activeMQ.amqp.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.qpid.jms.JmsConnectionFactory;

public class HelloTopicReceive {

	public HelloTopicReceive() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws JMSException {

//		String connectionURI = "amqp://localhost:5672";
		String connectionURI = "amqp://192.168.2.149:5672";
		ConnectionFactory connectionFactory = new JmsConnectionFactory(connectionURI);
		Connection connection = connectionFactory.createConnection();// 创建连接
		connection.setClientID("xxxxxxxx");
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);// 打开会话

		String destinationName = "demoTopic_anders";

		Topic dest = session.createTopic(destinationName);// 消息目的地

		MessageConsumer consumer = session.createDurableSubscriber(dest,"abb");

		connection.start();

		int c = 0;

		while (true) {

			System.out.println(c);
			Message message = consumer.receive();
			String jmsMessageID = message.getJMSMessageID();

			TextMessage textMessage = (TextMessage) message;

			String text = textMessage.getText();

			System.out.println("从ActiveMQ取回一条消息:jmsMessageID= "+jmsMessageID+":" + text);

			if(c>1000){
				break;
			}
			c++;

		}
		consumer.close();
		session.close();
		connection.close();

	}
}
