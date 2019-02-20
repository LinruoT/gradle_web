package bitter.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller //controller注解基于component注解
@RequestMapping({"/","/homepage"})//request mapping拆分后，路径映射部分放到类级别上
public class HomeController {
    @RequestMapping(method = RequestMethod.GET) //request mapping拆分后，http方法映射部分仍然在方法级别
    public String home(Model model) {//视图名home解析为/WEB-INF/views/home.jsp
        model.addAttribute("time", new Date());
        return "/WEB-INF/spring/home.html";
    }
}
