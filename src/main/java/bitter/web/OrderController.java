package bitter.web;

import bitter.domain.Bittle;
import bitter.mongo.Item;
import bitter.mongo.Order;
import bitter.mongo.db.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * todo: 由 mongoDB 保存 order
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository=orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)//处理/bittles页面 GET方法：把bittleRepository的列表填充到模型。model是Map （key-value集合）
    public String orders(Model model, @RequestParam(value = "max",defaultValue = ""+Long.MAX_VALUE) long max, //含有两个参数max和count，路径是/bittles?count=20
                          @RequestParam(value = "count",defaultValue = "20")int count) {
        List<Order> orderList=orderRepository.findAll(new Sort(Sort.Direction.DESC,"_id"));
        for(Order oneOrder:orderList) {
            System.out.println(oneOrder.getId()+oneOrder.getCustomer()+oneOrder.getType());
        }
        System.out.println("creating test order");
        Order order = new Order();
        List<Item> items = new LinkedList<>();
        int k=(int)(1+Math.random()*(10-1+1));
        for(int i=0;i<k;i++){
            Item item = new Item();
            item.setProduct("sdk"+(int)(Math.random()*(10-1+1)));
            item.setPrice((int)(Math.random()*(10-1+1)));
            item.setQuantity(1);
            items.add(item);
        }
        k=(int)(1+Math.random()*(3));
        if (k==1) order.setCustomer("billy");
        else if(k==2) order.setCustomer("steve");
        else order.setCustomer("mile");
        order.setType("gaoduanhei maimaimai test");
        order.setItems(items);

        System.out.println("saving test order to mongo");
        orderRepository.save(order);
        System.out.println("saved");
        model.addAttribute("orderList",orderList);
        return "orders";
    }
}
