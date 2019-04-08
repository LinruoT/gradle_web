package bitter.config;

import bitter.web.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

//DispatcherServletInitializer会自动配置DispatcherServlet和Spring应用上下文Context
//是传统web.xml代替方案
public class BitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    //设置profile的值，在选择数据源时会用到
    @Override
    public void onStartup(ServletContext context) throws ServletException {
        super.onStartup(context);
        String activeProfile = System.getProperty("your.profile.property");
        System.out.println("检测到（your.profile.property）为"+activeProfile);
        if (activeProfile == null) {
            activeProfile = "production"; // or whatever you want the default to be
        }
        System.out.println("设置spring.profiles.active为"+activeProfile);
        context.setInitParameter("spring.profiles.active", activeProfile);
    }

    //配置servlet过滤器：把charset设置成utf-8，在security里面配置filter
//    @Override
//    protected Filter[] getServletFilters() {
//        System.out.println("开始创建characterEncodingFilter");
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("UTF-8");
//        characterEncodingFilter.setForceEncoding(true);
//        return new Filter[] {characterEncodingFilter};
//    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override//重载customizeRegistration，对dispatcherServlet进行额外配置
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("",4194304,8388608,0));
    }
}