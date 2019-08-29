package bitter.alertHandles;

import bitter.mongo.Order;
import bitter.mongo.db.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * TODO: Process received hello.queue messages
 * TODO: Send Email
 *
 * @author Billy Lin
 */
@Component
public class HelloHandler {
    private OrderRepository orderRepository;
    private JavaMailSender javaMailSender;
    @Autowired
    public HelloHandler(OrderRepository orderRepository, JavaMailSender javaMailSender) {
        this.orderRepository = orderRepository;
        this.javaMailSender = javaMailSender;
    }
    public void handleHello(String message) {
        System.out.println(message);
        //新建一条order，存入mongoDB
        Order order = new Order();
        order.setCustomer(message);
        order.setType("test: received from rabbitMQ");
        orderRepository.save(order);

        //发送简单邮件
        if(message.contains("email")) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("ted_163mail@163.com");
            mail.setTo("814536088@qq.com");
            mail.setSubject("a new bittle contains 'email'");
            mail.setText(message + " a new bittle received from rabbitMQ");
            javaMailSender.send(mail);
        }
    }
}
