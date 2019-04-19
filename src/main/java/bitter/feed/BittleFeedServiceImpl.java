package bitter.feed;

import bitter.domain.Bittle;
import bitter.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO:stomp消息，当保存bittle会被调用，判断是否@用户
 *
 * @author Billy Lin
 */
@Service
public class BittleFeedServiceImpl implements BittleFeedService{
    private SimpMessagingTemplate messaging;
    private Pattern pattern= Pattern.compile("\\@(\\S+)");
    @Autowired //WebSocketStompConfig里传来的SimpMessagingTemplate
    public BittleFeedServiceImpl(SimpMessagingTemplate messaging) {
        this.messaging= messaging;
    }
    @Override
    public void broadcastBittle(Bittle bittle) {
        String user1 = bittle.getBitter().getUsername();
        System.out.println("broadcast: "+user1+"发了一条post");
        try {
            //公开广播
        messaging.convertAndSend("/topic/bittlefeed", bittle);
        //@用户，只是SendToUser
        Matcher matcher = pattern.matcher(bittle.getMessage());
        if (matcher.find()) {
            String user2 = matcher.group(1);
            System.out.println(user1+"at了"+user2);
            messaging.convertAndSendToUser(user2, "/queue/notifications",
                    new Notification("You just got mentioned!"));
        }
        } catch (Exception e) {
            System.out.println("bittleFeedService出错了");
            e.printStackTrace();
        }

    }
}
