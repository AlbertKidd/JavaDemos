package com.kidd.demos.amq.spring.embedded;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Kidd
 */
public class Listener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try{
            if (message instanceof TextMessage){
                TextMessage textMessage = (TextMessage)message;
                String text = textMessage.getText();
                System.out.println("我收到了：" + text);
            }else if (message instanceof MapMessage){
                MapMessage mapMessage = (MapMessage)message;
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
