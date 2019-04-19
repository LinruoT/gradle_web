package bitter.web;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import bitter.domain.Bitter;
import bitter.domain.Bittle;
import bitter.domain.Notification;
import bitter.feed.BittleFeedService;
import bitter.web.BittleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;

/**
 * TODO: stomp消息
 *
 * @author Billy Lin
 */

@Controller
public class BitterMessageController {
    private BittleRepository bittleRepository;
    private BitterRepository bitterRepository;
    private BittleFeedService bittleFeedService;

    @Autowired
    public BitterMessageController(BittleRepository bittleRepository,BitterRepository bitterRepository,BittleFeedService bittleFeedService) {
        this.bittleRepository=bittleRepository;
        this.bitterRepository=bitterRepository;
        this.bittleFeedService=bittleFeedService;
    }

    @MessageMapping("/bittle") //收听bittle的消息，类似mvc controller
    @SendToUser("/queue/notifications") //返回消息给notifications-user
    public Notification handleBittle(Principal principal, BittleForm bittleForm) {
        System.out.println("bittle收到stomp消息了");
        Bitter bitter=bitterRepository.findByUsername(principal.getName());
        Bittle bittle=new Bittle(null,bitter,bittleForm.getMessage(),new Date(),bittleForm.getLongitude(),bittleForm.getLatitude());
        bittle=bittleRepository.save(bittle);
        bittleFeedService.broadcastBittle(bittle);
        return new Notification("Saved Bittle: "+bittle.getId()+" author: "+principal.getName());
    }



}
