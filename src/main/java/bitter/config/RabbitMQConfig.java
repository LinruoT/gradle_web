package bitter.config;

import bitter.alertHandles.HelloHandler;
import bitter.domain.Bittle;
import com.rabbitmq.client.Channel;
import constant.ConfigConsts;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitAccessor;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(ConfigConsts.RABBITMQ_HOST);
        factory.setPort(ConfigConsts.RABBITMQ_PORT);
        factory.setUsername(ConfigConsts.RABBITMQ_USERNAME);
        factory.setPassword(ConfigConsts.RABBITMQ_PASSWORD);
        return factory;
    }
    @Bean
    public RabbitAdmin rabbitAdmin() {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory());
        admin.setAutoStartup(true);
        admin.declareExchange(new DirectExchange("hello.exchange"));
        admin.declareQueue(new Queue("hello.queue"));
        admin.declareBinding(new Binding("hello.queue",
                Binding.DestinationType.QUEUE,
                "hello.exchange",
                "hello.routing",
                null));
        return admin;
    }
    @Bean
    public AmqpTemplate rabbitTemplate() {
        System.out.println("rabbitTemplate");
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }


    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(HelloHandler helloHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        //队列可以是多个，参数是String的数组
        container.setQueueNames("hello.queue");
        MessageListenerAdapter adapter = new MessageListenerAdapter(helloHandler);
        adapter.setDefaultListenerMethod("handleHello");
        container.setMessageListener(adapter);
        return container;
    }


}
