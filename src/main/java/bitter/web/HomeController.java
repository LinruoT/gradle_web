package bitter.web;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller //controller注解基于component注解
@RequestMapping({"/","/homepage"})//request mapping拆分后，路径映射部分放到类级别上
public class HomeController {
    private BitterRepository bitterRepository;private BittleRepository bittleRepository;
    @Autowired
    public HomeController(BitterRepository bitterRepository,BittleRepository bittleRepository) { //构造器
        this.bittleRepository=bittleRepository;
        this.bitterRepository=bitterRepository;
    }


    @RequestMapping(method = RequestMethod.GET) //request mapping拆分后，http方法映射部分仍然在方法级别
    public String home(Model model) {//视图名home解析为/WEB-INF/views/home.jsp
        int bittleCount = (int)bittleRepository.count();
        int bitterCount = (int)bitterRepository.count();
        model.addAttribute("time", new Date());
        model.addAttribute("bittleCount",bittleCount);
        model.addAttribute("bitterCount",bitterCount);
        return "home";
    }
}
