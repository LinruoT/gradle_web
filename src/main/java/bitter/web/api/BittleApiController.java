package bitter.web.api;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import bitter.domain.Bittle;
import bitter.service.BittleService;
import bitter.web.error.BittleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
    public Bittle bittleById(@PathVariable Long id) {
        System.out.println("api get /bittles/{id}");
        Bittle bittle = bittleRepository.findOneWithCache(id);
        if(bittle==null) throw new BittleNotFoundException();
        return bittleRepository.findOne(id);
    }
    //同时返回location（新建资源的位置）,头部信息
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Bittle> saveBittle(@RequestBody Bittle bittle, UriComponentsBuilder ucb) {
        System.out.println("api post /bittles");
        bittleService.addBittle(bittle);
        Bittle saved = bittleRepository.save(bittle);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb
                .path("/bittles/")
                .path(String.valueOf(saved.getId()))
                .build().toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<>(saved,headers, HttpStatus.CREATED);
    }

    @ExceptionHandler(BittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error bttleNotFound(BittleNotFoundException e) {

        return new Error("Bittle not found "+e.getMessage());
    }


}
