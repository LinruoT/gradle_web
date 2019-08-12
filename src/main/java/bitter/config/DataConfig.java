package bitter.config;

import bitter.data.BitterRepository;
import bitter.domain.Bitter;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Configuration
@EnableTransactionManagement



//mybatis自动扫描mapper注册
@MapperScan(value = "mbg.dao")



@EnableJpaRepositories(basePackages="bitter.data")//spring data查找扩展自JpaRepository的接口，自动生成实现
public class DataConfig implements TransactionManagementConfigurer{
    @Resource(name="transactionManager")
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
                "?useUnicode=true&autoReconnect=true" +
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
                "?useUnicode=true&autoReconnect=true" +
                "&serverTimezone=Asia/Shanghai&useSSL=false");
        ds.setUsername("billyvps");
        ds.setPassword("billy11111111");
        ds.setInitialSize(5);
        return ds;
    }
    //    @Bean
//    public JdbcOperations jdbcTemplate(DataSource dataSource) {
//        return  new JdbcTemplate(dataSource);
//    }

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

    //only hibernate
//    @Bean
//    public SessionFactory sessionFactoryBean(DataSource dataSource) {
//        try {
//            LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
//            lsfb.setDataSource(dataSource);
//            lsfb.setPackagesToScan("bitter.domain");
//            Properties props = new Properties();
//            props.setProperty("dialect","org.hibernate.dialect.MySQLDialect");
//            props.setProperty("current_session_context_class","thread");
//            lsfb.setHibernateProperties(props);
//            lsfb.afterPropertiesSet();
//            SessionFactory object = lsfb.getObject();
//            System.out.println("sessionFactoryBean成功！"+object);
//            return object;
//        } catch (IOException e) {
//            System.out.println("sessionFactoryBean错误！");
//            return null;
//        }
//    }
//    @Bean(name = "txManager2")
//    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
//        System.out.println("sessionFactory: "+sessionFactory);
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory);
//        return transactionManager;
//    }

    //JPA-Hibernate
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");

        System.out.println("JpaPropertyMap:");
        for (Map.Entry<String, Object> entry : adapter.getJpaPropertyMap().entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

        }
        return adapter;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        //不需要设置persistence.xml
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcemfb.setPackagesToScan("bitter.domain");
//        Properties jpaProperties = new Properties();
//        //查看原来jpaProperty
//        System.out.println("original JpaPropertyMap:");
//        for (Map.Entry<String, Object> entry : lcemfb.getJpaPropertyMap().entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//        //为了解决 懒加载和session close的问题
//        //jpaProperties.put("open-in-view","true"); //方案一：https://blog.csdn.net/blueheart20/article/details/52912023
//        jpaProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
//        jpaProperties.put("hibernate.show_sql","true"); //
//        jpaProperties.put("hibernate.enable_lazy_load_no_trans","true"); //方案二：https://stackoverflow.com/questions/21574236/how-to-fix-org-hibernate-lazyinitializationexception-could-not-initialize-prox
//        lcemfb.setJpaProperties(jpaProperties);
//        //查看新的jpaProperty
//        System.out.println("new JpaPropertyMap:");
//        for (Map.Entry<String, Object> entry : lcemfb.getJpaPropertyMap().entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//        }
        return lcemfb;
    }
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        System.out.println("emf: "+emf);
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }



//learn-mybatis
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return factoryBean.getObject();
    }
    @Bean
    public BitterRepository bitterRepository(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate.getMapper(BitterRepository.class);
    }





    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager2;
    }

    //异常转换为spring异常
    @Bean
    public BeanPostProcessor persistenceTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
