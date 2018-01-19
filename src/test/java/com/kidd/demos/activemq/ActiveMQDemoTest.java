package com.kidd.demos.activemq;

import com.kidd.demos.activeMQ.ActiveMQDemo;
import org.testng.annotations.Test;

/**
 * @author Kidd
 * CreateTime 2018/1/18.
 */
public class ActiveMQDemoTest {

    @Test
    public void testSendQueue() throws Exception {
        ActiveMQDemo.sendMsg(ActiveMQDemo.CONN_OPEN_WIRE, ActiveMQDemo.DestType.queue, "KiddQueue", "QueueMsg");
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

}