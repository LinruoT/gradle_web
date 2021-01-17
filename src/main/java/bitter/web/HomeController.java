package bitter.web;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import bitter.domain.Bitter;
import bitter.service.BillyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Date;

@Controller //controller注解基于component注解
@RequestMapping({"/","/homepage"})//request mapping拆分后，路径映射部分放到类级别上
public class HomeController {
    private BitterRepository bitterRepository;
    private BittleRepository bittleRepository;
    private BillyService billyService;

    @Autowired
    public HomeController(BitterRepository bitterRepository,BittleRepository bittleRepository, BillyService billyService) { //构造器
        this.bittleRepository=bittleRepository;
        this.bitterRepository=bitterRepository;
        this.billyService = billyService;
    }


    @RequestMapping(method = RequestMethod.GET) //request mapping拆分后，http方法映射部分仍然在方法级别
    public String home(Model model, Principal principal) {//视图名home解析为/WEB-INF/views/home.jsp
        System.out.println("billy location:");
        System.out.println(billyService.getLocation());

        int bittleCount = (int)bittleRepository.count();
        int bitterCount = (int)bitterRepository.count();
        Bitter bitter = new Bitter();
        if(principal!=null) {
            System.out.println("当前用户是 "+principal.getName());
            bitter=bitterRepository.findByUsername(principal.getName());
        }
        model.addAttribute("time", new Date());
        model.addAttribute("bittleCount",bittleCount);
        model.addAttribute("bitterCount",bitterCount);
        model.addAttribute("bitter",bitter);
        return "home";
    }
}
