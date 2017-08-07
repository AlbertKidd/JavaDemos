package com.demos.activeMQ.spring.embeded;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;
import java.util.Map;

/**
 * Created by Kidd on 2017/7/28.
 */
@Getter
@Setter
public class Publisher {

    private JmsTemplate jmsTemplate;
    private Destination[] destinations;

    public void sendTextMessage(final String text){
        for (Destination destination : destinations){
            jmsTemplate.send(destination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(text);
                }
            });
        }
    }

    public void sendMapMessage(final Map<String, Object> map){
        for (Destination destination : destinations){
            jmsTemplate.send(destination, new MessageCreator() {
                public Message createMessage(Session session) throws JMSException {
                    MapMessage message = session.createMapMessage();
                    for (Map.Entry entry : map.entrySet()){
                        message.setObject((String)entry.getKey(), entry.getValue());
                    }
                    return message;
                }
            });
        }
    }
}
