package bitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * TODO:
 *
 * @author Billy Lin
 */

@Configuration
public class EmailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
//        mailSender.setPort(465);
        mailSender.setUsername("ted_163mail@163.com");
        mailSender.setPassword("Tedlrt321852!");
//        Properties properties = new Properties();
//        properties.setProperty()
//
//        mailSender.setJavaMailProperties();
        return mailSender;
    }

}
