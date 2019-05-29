package bitter.config;

import bitter.web.WebConfig;
import bitter.web.WebSocketStompConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

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
        return new Class<?>[] { WebConfig.class, WebSocketStompConfig.class};
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

        System.out.println("设置log4j");
        context.addListener(Log4jConfigListener.class);
        //context.addListener(WebSessionListener.class);
        context.setInitParameter("webAppRootKey", "spring4.root");
        context.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        context.setInitParameter("log4jRefreshInterval", "10000");

        System.out.println("设置spring.profiles.active为"+activeProfile);
        context.setInitParameter("spring.profiles.active", activeProfile);

        //super.onStartup(context);
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
        registration.setAsyncSupported(true);
    }
}