package bitter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"bitter"},excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})  //自动扫描非web组件
public class RootConfig {
}
