package com.kidd.demos.jgroups;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;

/**
 * @author Kidd
 * CreateTime 2017/12/8.
 */
public class ReceiverTest {

    @Test
    public void testReceiver() throws Exception{
        File file = new File("/");
        String routerHost = "192.168.2.149[17149]";
        InputStream inputStream = MyReceiver.class.getResourceAsStream("/conf/jgroups/udp.xml");
        JChannel channel = new JChannel(inputStream);
        channel.setName("First Channel");
        channel.setReceiver(new MyReceiver());
        Message message = new Message();
        message.setBuffer("test msg".getBytes());
        channel.getState(null, 100000);
        channel.send(message);
        Thread.sleep(100000);
    }
}