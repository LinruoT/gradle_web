package bitter.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MqConfig {
    @Bean
    ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//        connectionFactory().setBrokerURL();
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
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(queue);
        return jmsTemplate;
    }
}
