package bitter.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

//    @Bean //使用嵌入式数据库
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql").build();
    }
//    @Bean //使用jdbc
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
    @Bean
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return  new JdbcTemplate(dataSource);
    }
}
