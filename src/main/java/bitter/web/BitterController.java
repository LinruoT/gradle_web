package bitter.web;


import bitter.Bitter;
import bitter.data.BitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

//处理/bitter的控制器（用户）
@Controller
@RequestMapping("/bitter")
public class BitterController {
    private BitterRepository bitterRepository;
    @Autowired
    public BitterController(BitterRepository bitterRepository) { //构造器
        this.bitterRepository=bitterRepository;
    }
//    @RequestMapping(value = "/register",method = RequestMethod.GET) //注册页面
//    public String showRegistration() {
//        return "registerForm";
//    }
    @RequestMapping(value = "/register",method = RequestMethod.GET) //注册页面
    public String showRegistration(Model model) {
        model.addAttribute(new Bitter());//添加key=bitter的属性
        return "registerForm";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST) //提交表单，bitter的属性会被form同名参数填充
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture,
                                      @Valid Bitter bitter,
                                      Errors errors,
                                      Model model,
                                      RedirectAttributes redirectAttributes) throws Exception {
        if(errors.hasErrors()) { return "registerForm"; }//有错误 则注册页面
        if(bitterRepository.save(bitter)==null) {
            throw new DuplicateBitterException();
        }
        profilePicture.transferTo(new File(bitter.getUsername()+"_"+
                new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"_"+
                profilePicture.getOriginalFilename())); //保存上传的图片

        model.addAttribute("username",bitter.getUsername());
        model.addAttribute("bitterId",bitter.getId());
        redirectAttributes.addFlashAttribute(bitter); //重定向时，相比url模版只能传递String，flash可以传递对象
        redirectAttributes.addAttribute("username",bitter.getUsername());

//        return "redirect:/bitter/"+bitter.getUsername();
        return "redirect:/bitter/{username}"; //比直接拼接字符串更加安全，model的其他属性会自动变成查询参数，/bitter/linruotian?bitterId=0
    }

//    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
//    public String showBitterProfile(@PathVariable String username, Model model) {
//        Bitter bitter=bitterRepository.findByUsername(username);
//        model.addAttribute(bitter);
//        return "profile";
//    }
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showBitterProfile(@PathVariable String username, Model model) {
        if (!model.containsAttribute("bitter")) {
            System.out.println("没有传来bitter属性，从数据库中读取");
            Bitter bitter = bitterRepository.findByUsername(username);
            model.addAttribute(bitter);
        }
        return "profile";
    }
}
