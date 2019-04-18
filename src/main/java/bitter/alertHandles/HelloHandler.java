package bitter.alertHandles;

import bitter.mongo.Order;
import bitter.mongo.db.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * TODO: Process received hello.queue messages
 *
 * @author Billy Lin
 */
@Component
public class HelloHandler {
    private OrderRepository orderRepository;
    @Autowired
    public HelloHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public void handleHello(String message) {
        System.out.println(message);
        Order order = new Order();
        order.setCustomer(message);
        order.setType("test: received from rabbitMQ");
        orderRepository.save(order);
    }
}
