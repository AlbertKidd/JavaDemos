package paho;

import lombok.extern.log4j.Log4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Kidd on 2017/7/14.
 */
@Log4j
public class PahoProducer {

    public static void main(String[] args) {

        String topic        = "topic1";
        String content      = "Kidd 消息推送3";
        int qos             = 2;
        String broker       = "tcp://localhost:61613";
        String clientId     = "JavaClient1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.setCleanSession(false);
            connectOptions.setUserName("admin");
            connectOptions.setPassword("password".toCharArray());
            log.info("connecting to broker: " + broker);
            client.connect(connectOptions);
            log.info("connected");
            log.info("publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            client.publish(topic, message);
            log.info("message published");
            client.disconnect();
            log.info("disconnected");
            System.exit(0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
}
