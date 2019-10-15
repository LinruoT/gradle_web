package bitter.config;

import bitter.security.jwt.JWTConfigurer;
import bitter.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    TokenProvider tokenProvider;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from Bitter where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_BITTER' from Bitter where username=?")
                .passwordEncoder(new StandardPasswordEncoder("gaoduanhei"));
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //测试rest才用
                .csrf()
                .disable()


                .formLogin()
                    .loginPage("/login") //需要登陆的时候会跳转到 /login
                    .defaultSuccessUrl("/")
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                //     * Specifies the {@link PersistentTokenRepository} to use. The default is to
                //     * use {@link TokenBasedRememberMeServices} instead.
                    //.tokenRepository(new InMemoryTokenRepositoryImpl())
                    .tokenValiditySeconds(2419200)
                    .key("bitterKey")
                .and()
                .httpBasic()
                    .realmName("Bitter")
                .and()
                .authorizeRequests()
                    .antMatchers("/homestomp").hasRole("BITTER")
                    .antMatchers("/bitter/**").authenticated()
                    .antMatchers("/api/auth").permitAll()
                    .antMatchers("/api/bittles").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
                .and()
                .requiresChannel()
                    .antMatchers("/").requiresInsecure()
                .and()
                    .apply(jwtConfigurer());
    }
    private JWTConfigurer jwtConfigurer() {
        return new JWTConfigurer(tokenProvider);
    }
}
