package bitter.api;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import bitter.domain.Bittle;
import bitter.service.BittleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@RestController是@ResponseBody和@Controller的组合注解。
@RequestMapping("/bittles")
public class BittleApiController {
    private BittleRepository bittleRepository;
    private BitterRepository bitterRepository;
    private BittleService bittleService;
    @Autowired//构造器
    public BittleApiController(BittleRepository bittleRepository, BitterRepository bitterRepository, BittleService bittleService) {
        this.bittleRepository=bittleRepository;
        this.bitterRepository=bitterRepository;
        this.bittleService=bittleService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Bittle> bittles(
            @RequestParam(value = "max", defaultValue = ""+Long.MAX_VALUE) long max,
            @RequestParam(value = "count",defaultValue = "20")int count) {
        System.out.println("api get /bittles");
        return bittleRepository.findBittles(max,count);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public Bittle saveBittle(@RequestBody Bittle bittle) {
        System.out.println("api post /bittles");
        bittleService.addBittle(bittle);
        return bittleRepository.save(bittle);
    }


}
