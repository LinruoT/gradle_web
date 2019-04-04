package bitter.web;

import bitter.redis.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private RedisTemplate<String, Product> redis;

    @Autowired
    public ProductController(RedisTemplate<String, Product> redis) {
        this.redis = redis;
    }

    @RequestMapping(method = RequestMethod.GET)//处理/bittles页面 GET方法：把bittleRepository的列表填充到模型。model是Map （key-value集合）
    public String products(Model model, @RequestParam(value = "max", defaultValue = "" + Long.MAX_VALUE) long max, //含有两个参数max和count，路径是/bittles?count=20
                           @RequestParam(value = "count", defaultValue = "20") int count) {

        List<Product> productList = new LinkedList<>();
        Product product;
        Product productToSave = new Product();
        productToSave.setName("beef");
        productToSave.setPrice(108);
        productToSave.setSku("6971150669185");
        System.out.println("saving test product: beef");
        redis.opsForValue().set(productToSave.getSku(),productToSave);
        System.out.println("getting products");
        product=redis.opsForValue().get("billy");
        productList.add(product);
        product=redis.opsForValue().get("steve");
        productList.add(product);
        product=redis.opsForValue().get("mile");
        productList.add(product);
        product=redis.opsForValue().get(productToSave.getSku());
        productList.add(product);
        model.addAttribute("productList",productList);
        return "products";
    }

}