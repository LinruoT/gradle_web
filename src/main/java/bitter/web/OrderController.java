package bitter.web;

import bitter.domain.Bittle;
import bitter.mongo.Order;
import bitter.mongo.db.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository=orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)//处理/bittles页面 GET方法：把bittleRepository的列表填充到模型。model是Map （key-value集合）
    public String bittles(Model model, @RequestParam(value = "max",defaultValue = ""+Long.MAX_VALUE) long max, //含有两个参数max和count，路径是/bittles?count=20
                          @RequestParam(value = "count",defaultValue = "20")int count) {
        List<Order> orderList=orderRepository.findAll();
        for(Order oneOrder:orderList) {
            System.out.println(oneOrder.getId()+oneOrder.getCustomer()+oneOrder.getType());
        }
        Order order = new Order();
        order.setCustomer("billy");
        order.setType("gaoduanhei maimaimai test");
        System.out.println("saving test order to mongo");
        orderRepository.save(order);
        System.out.println("saved");
        model.addAttribute("orderList",orderList);
        model.addAttribute(new BittleForm());
        return "orders";
    }
}
