package bitter.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:/billy.properties")
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"bitter", "bitter.web.interceptor"},excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})  //自动扫描非web组件
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
