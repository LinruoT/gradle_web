package bitter.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

//用于启用spring security
//把servlet的filter 委托给 spring上下文
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
    /**
     * 配置编码
     */
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext)
    {
        System.out.println("开始创建characterEncodingFilter");
        FilterRegistration.Dynamic characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }

}
