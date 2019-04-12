package bitter.mq;

import bitter.domain.Bittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class AlertServiceImpl implements AlertService {
    private JmsOperations jmsOperations;
    @Autowired
    public AlertServiceImpl(JmsOperations jmsOperations) { //注入jmsTemplate
        this.jmsOperations = jmsOperations;
    }
    @Override
    public void sendBittleAlert(String msg) {
//        jmsOperations.send("bittle.alert.queue", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(bittle);
//            }
//        }); //用lambda代替
        jmsOperations.send("bittle.alert.queue",
                (Session session) -> session.createObjectMessage(msg));

    }
    @Override
    public void sendBittleAlertToDefault(String msg) {
        jmsOperations.send((Session session) -> session.createObjectMessage(msg));
    }
}
