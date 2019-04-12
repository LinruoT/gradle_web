package bitter.web;

import bitter.data.BitterRepository;
import bitter.domain.Bitter;
import bitter.domain.Bittle;
import bitter.data.BittleRepository;
import bitter.mq.AlertService;
import bitter.service.BittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

//处理/bittles的控制器（推文）
@Controller
@RequestMapping("/bittles")
public class BittleController {
    private BittleRepository bittleRepository;
    private BitterRepository bitterRepository;
    private BittleService bittleService;
    private AlertService alertService;
    @Autowired//构造器传入bittles数据，bittleRepository的实现是JdbcBittleRepository
    public BittleController(BittleRepository bittleRepository, BitterRepository bitterRepository,
                            BittleService bittleService, AlertService alertService) {
        this.bittleRepository=bittleRepository;
        this.bitterRepository=bitterRepository;
        this.bittleService=bittleService;
        this.alertService=alertService;
    }

    @RequestMapping(method = RequestMethod.GET)//处理/bittles页面 GET方法：把bittleRepository的列表填充到模型。model是Map （key-value集合）
    public String bittles(Model model,@RequestParam(value = "max",defaultValue = ""+Long.MAX_VALUE) long max, //含有两个参数max和count，路径是/bittles?count=20
                          @RequestParam(value = "count",defaultValue = "20")int count) {
        List<Bittle> bittleList=bittleRepository.findBittles(max,count); //custom方法，jpa
//        List<Bittle> bittleList=bittleRepository.readAllByIdNotNullOrderByIdDesc(); //spring data
        for(Bittle oneBittle:bittleList) {
            System.out.println(oneBittle.getMessage());
        }
        model.addAttribute("bittleList",bittleList);
        model.addAttribute(new BittleForm());
        return "bittles";
    }

    @RequestMapping(value = "/{bittleId}",method = RequestMethod.GET) //路径是/bittles/123
    public String bittle(@PathVariable Long bittleId, Model model) {
        Bittle bittle=null;
        try {
            bittle=bittleRepository.findOneWithCache(bittleId);
        } catch (Exception e) {
            System.out.println("find bittle error");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if(bittle==null) {
            throw new BittleNotFoundException();
        }
        model.addAttribute("bittle",bittle);
        return "bittle";
    }
    @RequestMapping(method = RequestMethod.POST) //接受表单，新建一个bittle
    public String saveBittle(@Valid BittleForm bittleForm, Errors errors, Principal principal) {
        if(errors.hasErrors()) { return "bittles"; }
        Bitter bitter=bitterRepository.findByUsername(principal.getName());
        Bittle bittle=new Bittle(null,bitter,bittleForm.getMessage(),new Date(),bittleForm.getLongitude(),bittleForm.getLatitude());
        bittleService.addBittle(bittle);
        if(bittleRepository.save(bittle).getId()<10) {
            throw new DuplicateBittleException();
        }
        return "redirect:/bittles";
    }

    @RequestMapping(value = "/testapi/{bittleId}",method = RequestMethod.GET)
    public String testApiBittle(@PathVariable Long bittleId,Model model) {
        System.out.println("testapi: /bittles/testapi/{bittleId}");
        Bittle bittle;
        RestTemplate restTemplate= new RestTemplate();
//        bittle = restTemplate.getForObject("http://localhost:8080/bittles/{bittle}",
        bittle = restTemplate.getForObject("http://b.linruotian.com/bittles/{bittle}",
                Bittle.class,bittleId);
        if(bittle==null) {
            throw new BittleNotFoundException();
        }
        model.addAttribute("bittle",bittle);

        //测试MQ
        System.out.println("test: sendBittleAlert To MQ");
        alertService.sendBittleAlert("gaoduanhei");
        alertService.sendBittleAlertToDefault("gaoduanhei-default");


        return "bittle";
    }
    //POST API还存在问题
    @RequestMapping(value = "/testapi",method = RequestMethod.GET)
    public String testapiBittleForm(Model model) {
        model.addAttribute(new BittleForm());
        return "testapibittle";
    }
    @RequestMapping(value = "/testapi",method = RequestMethod.POST)
    public String testApiSaveBittle(BittleForm bittleForm) {
        System.out.println("testapi: POST /bittles/testapi");
        RestTemplate restTemplate = new RestTemplate();
        Bitter bitter=bitterRepository.findByUsername("billy");
        Bittle bittle=new Bittle(null,bitter,bittleForm.getMessage(),new Date(),bittleForm.getLongitude(),bittleForm.getLatitude());

        return restTemplate.postForLocation("http://b.linruotian.com/bittles",bittle).toString();
    }

    //已经集中在AppWideExceptionHandler了
//    @ExceptionHandler(DuplicateBittleException.class) //处理此控制器所有方法抛出的DuplicateBittleException异常
//    public String handleDuplicateBittle() {
//        return "error/duplicate";
//
//    }
}
