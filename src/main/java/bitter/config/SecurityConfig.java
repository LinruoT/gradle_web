package bitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("billy").password("mm321852").roles("BITTER").and()
//                .withUser("admin").password("tedlrt321852mm321852").roles("BITTER","ADMIN");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from Bitter where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_BITTER' from Bitter where username=?")
                .passwordEncoder(new StandardPasswordEncoder("gaoduanhei"));
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开发环境手动关闭csrf
//        http .csrf().disable();
        http
                .formLogin()
                    .loginPage("/login") //需要登陆的时候会跳转到 /login
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                    .tokenRepository(new InMemoryTokenRepositoryImpl())
                    .tokenValiditySeconds(2419200)
                    .key("bitterKey")
                .and()
                .httpBasic()
                    .realmName("Bitter")
                .and()
                .authorizeRequests()
//                    .antMatchers("/bitter/**").hasRole("BITTER")
//                    .antMatchers("/bittles").authenticated()
                    .anyRequest().permitAll()
                .and()
                .requiresChannel()
                    .antMatchers("/").requiresInsecure();
    }

}
