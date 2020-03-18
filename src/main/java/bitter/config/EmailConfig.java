package bitter.config;

import constant.ConfigConsts;
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
        mailSender.setHost(ConfigConsts.MAIL_HOST);
//        mailSender.setPort(465);
        mailSender.setUsername(ConfigConsts.MAIL_USERNAME);
        mailSender.setPassword(ConfigConsts.MAIL_PASSWORD);
//        Properties properties = new Properties();
//        properties.setProperty()
//
//        mailSender.setJavaMailProperties();
        return mailSender;
    }

}
