package client.kafka;

import org.springframework.kafka.annotation.KafkaListener;
public class Listener {
    @KafkaListener(topics = {"topicA"})
    public void recieve(String message)  {
        System.out.println(message);
    }
}
