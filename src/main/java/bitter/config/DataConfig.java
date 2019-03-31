package bitter.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class DataConfig implements TransactionManagementConfigurer{
    @Resource(name="txManager2")
    private PlatformTransactionManager txManager2;
//    @Bean //使用嵌入式数据库
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//                .addScript("schema.sql").build();
//    }
    @Profile("development")
    @Bean //使用jdbc
    public DataSource dataSourceBillyvpsMysql() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://billyvps.cf:3306/bitter" +
                "?useUnicode=true&characterEncoding=UTF-8" +
                "&serverTimezone=Asia/Shanghai&useSSL=false");
        ds.setUsername("billyvps");
        ds.setPassword("billy11111111");
        return ds;
    }
    @Profile("production")
    @Bean //使用连接池
    public BasicDataSource dataSourceDBCP() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://billyvps.cf:3306/bitter" +
                "?useUnicode=true&characterEncoding=UTF-8" +
                "&serverTimezone=Asia/Shanghai&useSSL=false");
        ds.setUsername("billyvps");
        ds.setPassword("billy11111111");
        ds.setInitialSize(5);
        return ds;
    }
    //在spring中集成hibernate
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
//        sfb.setDataSource(dataSource);
//        sfb.setPackagesToScan(new String[] {"bitter.domain"});
////        sfb.setAnnotatedClasses(Bittle.class,Bittle.class); //packagesToScan更好
//        Properties properties = new Properties();
//        properties.setProperty("dialect","org.hibernate.dialect.MySQLDialect");
//        sfb.setHibernateProperties(properties);
//        System.out.println("sfb.getObject: "+sfb.getObject());
//        return sfb;
//    }
    @Bean
    public SessionFactory sessionFactoryBean(DataSource dataSource) {
        try {
            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
            lsfb.setDataSource(dataSource);
            lsfb.setPackagesToScan("bitter.domain");
            Properties props = new Properties();
            props.setProperty("dialect","org.hibernate.dialect.MySQLDialect");
            props.setProperty("current_session_context_class","thread");
            lsfb.setHibernateProperties(props);
            lsfb.afterPropertiesSet();
            SessionFactory object = lsfb.getObject();
            System.out.println("sessionFactoryBean成功！"+object);
            return object;
        } catch (IOException e) {
            System.out.println("sessionFactoryBean错误！");
            return null;
        }
    }
//    @Bean
//    public JdbcOperations jdbcTemplate(DataSource dataSource) {
//        return  new JdbcTemplate(dataSource);
//    }
    @Bean(name = "txManager2")
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        System.out.println("sessionFactory: "+sessionFactory);
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager2;
    }

    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
