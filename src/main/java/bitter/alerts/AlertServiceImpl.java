package bitter.alerts;

import bitter.domain.Bittle;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_TEXT_PLAIN;

@Service
public class AlertServiceImpl implements AlertService {
    private AmqpTemplate rabbit;
    @Autowired
    public AlertServiceImpl(AmqpTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendBittleAlert(Bittle bittle) {
        try {
            System.out.println("send hello world");
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(CONTENT_TYPE_TEXT_PLAIN);
            rabbit.send("hello.exchange","hello.routing",
                    new Message(("Hello World! "+bittle.getMessage()).getBytes(),messageProperties));

            rabbit.convertAndSend(bittle);
        } catch (Exception e) {
            System.out.println("报错：rabbit.convertAndSend(bittle)");
            e.printStackTrace();
        }

    }
}
