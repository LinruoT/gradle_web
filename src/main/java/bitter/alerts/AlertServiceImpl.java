package bitter.alerts;

import bitter.domain.Bittle;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            rabbit.send("hello.exchange","hello.routing",
                    new Message("Hello World! gaoduanhei".getBytes(),new MessageProperties()));

            rabbit.convertAndSend(bittle);
        } catch (Exception e) {
            System.out.println("报错：rabbit.convertAndSend(bittle)");
            e.printStackTrace();
        }

    }
}
