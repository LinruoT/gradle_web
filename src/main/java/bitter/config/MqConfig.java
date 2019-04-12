package bitter.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

@Configuration
public class MqConfig {
    @Bean
    ActiveMQConnectionFactory connectionFactory() {
        System.out.println("开始建立ActiveMQ链接");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    return connectionFactory;
    }
    @Bean
    ActiveMQQueue queue() {
        ActiveMQQueue queue = new ActiveMQQueue("bitter.queue");
        return queue;
    }
    @Bean
    ActiveMQTopic topic() {
        ActiveMQTopic topic = new ActiveMQTopic("bitter.topic");
        return topic;
    }
    @Bean
    JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory,ActiveMQQueue queue) {
        System.out.println("测试ActiveMQConnection");
        try {
            Connection connection =connectionFactory.createConnection();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queueTest = session.createQueue("HelloActiveMQ");
            MessageProducer producer = session.createProducer(queueTest);
            for (int i = 0; i < 10; i++) {
                producer.send(session.createTextMessage("activeMQ,你好!"+i));
            }
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println("正在创建jmsTemplate");
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(queue);
        jmsTemplate.afterPropertiesSet();
        jmsTemplate.getConnectionFactory();
        System.out.println("12345");
        //只要有这两句就会出错
        jmsTemplate.send("bittle.alert.queue",
                (Session session) -> session.createObjectMessage("gaoduanhei"));
        return jmsTemplate;
    }
}
