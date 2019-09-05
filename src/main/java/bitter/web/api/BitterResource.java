package bitter.web.api;


import bitter.data.BitterRepository;
import bitter.domain.Bitter;
import bitter.service.BitterService;
import bitter.web.error.BitterNotFoundException;
import bitter.web.error.DataIllegelException;
import bitter.web.error.DuplicateBitterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BitterResource {
    private final BitterRepository bitterRepository;
    private final BitterService bitterService;
    @Autowired
    public BitterResource(BitterRepository bitterRepository, BitterService bitterService){
        this.bitterRepository=bitterRepository;
        this.bitterService=bitterService;
    }


    @RequestMapping(value = "/bitters/{username}",method = RequestMethod.GET)
    public ResponseEntity<Bitter> getBitter(@PathVariable String username) {
        Bitter bitter = bitterRepository.findByUsername(username);
        if(bitter==null) throw new BitterNotFoundException();
        return new ResponseEntity<>(bitter, HttpStatus.OK);
    }
    @RequestMapping(value = "/bitters", method = RequestMethod.GET)
    public ResponseEntity<List<Bitter>> getAllBitters() {
        List<Bitter> bitters = bitterRepository.findAll();
        return new ResponseEntity<>(bitters,HttpStatus.OK);
    }
    @RequestMapping(value = "/bitters",method = RequestMethod.POST)
    public ResponseEntity<Bitter> saveBitter(@RequestBody @Valid Bitter bitter, Errors errors, UriComponentsBuilder ucb) {
        if(errors.hasErrors()) { throw new DataIllegelException(); }//有错误 则注册页面
        if(bitterRepository.findByUsername(bitter.getUsername())!=null) {
            throw new DuplicateBitterException();
        }
        bitter.setPassword(new StandardPasswordEncoder("gaoduanhei").encode(bitter.getPassword()));
        Bitter saved = bitterRepository.save(bitter);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/api/bitter/")
                .path(String.valueOf(saved.getId()))
                .build().toUri();
        headers.setLocation(locationUri);
        return new ResponseEntity<>(saved,headers,HttpStatus.CREATED);
    }
    @RequestMapping(value = "/bitters", method = RequestMethod.PUT)
    public ResponseEntity<Bitter> updateBitter(@RequestBody Bitter bitter) {
        Bitter existingBitter = bitterRepository.findByUsername(bitter.getUsername());

        if(existingBitter==null) throw new BitterNotFoundException();
        Bitter updatedBitter = bitterRepository.save(bitter);
        return new ResponseEntity<>(updatedBitter,HttpStatus.OK);
    }
    @RequestMapping(value = "/bitters/{username}",method = RequestMethod.DELETE)
    public ResponseEntity deleteBitter(@PathVariable String username) {
        Bitter existingBitter = bitterRepository.findByUsername(username);
        if(existingBitter!=null)
            bitterRepository.delete(existingBitter);
        return new ResponseEntity(HttpStatus.OK);
    }

    //处理异常
    @ExceptionHandler(BitterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error bitterNotFound(BitterNotFoundException e) {

        return new Error(4,"bitter not found "+e.getMessage());
    }
    //处理异常
    @ExceptionHandler(DuplicateBitterException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error duplicateBitter(DuplicateBitterException e) {

        return new Error(2,"duplicate bitter "+e.getMessage());
    }
    @ExceptionHandler(DataIllegelException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error duplicateBitter(DataIllegelException e) {

        return new Error(2,"data illegal "+e.getMessage());
    }

}
