package bitter.web;

import bitter.Bittle;
import bitter.data.BittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

//处理/bittles的控制器（推文）
@Controller
@RequestMapping("/bittles")
public class BittleController {
    private BittleRepository bittleRepository;
    @Autowired//构造器传入bittles数据，bittleRepository的实现是JdbcBittleRepository
    public BittleController(BittleRepository bittleRepository) {
        this.bittleRepository=bittleRepository;
    }

    @RequestMapping(method = RequestMethod.GET)//处理/bittles页面 GET方法：把bittleRepository的列表填充到模型。model是Map （key-value集合）
    public String bittles(Model model,@RequestParam(value = "max",defaultValue = ""+Long.MAX_VALUE) long max, //含有两个参数max和count，路径是/bittles?count=20
                          @RequestParam(value = "count",defaultValue = "20")int count) {
        List<Bittle> bittleList=bittleRepository.findBittles(max,count);
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
            bittle=bittleRepository.findOne(bittleId);
        } catch (Exception e) {

        }
        if(bittle==null) {
            throw new BittleNotFoundException();
        }
        model.addAttribute("bittle",bittle);
        return "bittle";
    }
    @RequestMapping(method = RequestMethod.POST) //接受表单，新建一个bittle
    public String saveBittle(BittleForm bittleForm) {
        Bittle bittle=new Bittle(bittleForm.getMessage(),new Date());
        bittleRepository.save(bittle);
        return "redirect:/bittles";
    }
    @ExceptionHandler(DuplicateBittleException.class) //处理此控制器所有方法抛出的DuplicateBittleException异常
    public String handleDuplicateBittle() {
        return "error/duplicate";

    }
}
