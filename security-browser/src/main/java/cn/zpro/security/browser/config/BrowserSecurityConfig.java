package cn.zpro.security.browser.config;

import cn.zpro.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author zhanggl
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/loginProcessing")
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require").permitAll()
                .antMatchers(securityProperties.getBrowser().getLoginUrl()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
