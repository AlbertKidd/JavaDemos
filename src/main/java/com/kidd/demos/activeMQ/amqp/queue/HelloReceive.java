package com.kidd.demos.activeMQ.amqp.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class HelloReceive {

	public HelloReceive() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws JMSException {

		// String connectionURI = "amqp://localhost:5672";
		String connectionURI = "amqp://192.168.2.149:5672";
		ConnectionFactory connectionFactory = new JmsConnectionFactory(connectionURI);
		Connection connection = connectionFactory.createConnection();// 创建连接

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);// 打开会话

		String destinationName = "demoQueue_anders";

		Destination dest = session.createQueue(destinationName);// 消息目的地

		MessageConsumer consumer = session.createConsumer(dest);

		connection.start();
		int c = 0;
		while (true) {
			System.out.println(c);
			Message message = consumer.receive();
			String jmsMessageID = message.getJMSMessageID();

			TextMessage textMessage = (TextMessage) message;

			String text = textMessage.getText();

			System.out.println("从ActiveMQ取回一条消息:jmsMessageID= " + jmsMessageID + ":" + text);
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
