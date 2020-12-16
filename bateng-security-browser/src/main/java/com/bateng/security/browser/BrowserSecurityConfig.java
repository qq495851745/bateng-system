package com.bateng.security.browser;

import com.bateng.core.properties.BrowserProperties;
import com.bateng.core.properties.BatengSecurityProperties;
import com.bateng.core.validate.code.ValidateCodeFilter;
import com.bateng.security.browser.authentication.BatengAuthenticationFailureHandler;
import com.bateng.security.browser.authentication.BatengAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BatengSecurityProperties batengSecurityProperties;

    @Autowired
    private BatengAuthenticationSuccessHandler batengAuthenticationSuccessHandler;

    @Autowired
    private BatengAuthenticationFailureHandler batengAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 提供加密工具
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("登录页面：" + batengSecurityProperties.getBrowserProperties().getLoginPage());

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(batengAuthenticationFailureHandler);
        validateCodeFilter.setBatengSecurityProperties(batengSecurityProperties);
        validateCodeFilter.afterPropertiesSet();
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // .httpBasic()
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/login")
                    .successHandler(batengAuthenticationSuccessHandler)
                    .failureHandler(batengAuthenticationFailureHandler)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(batengSecurityProperties.getBrowserProperties().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                    .authorizeRequests()
                    .antMatchers("/authentication/require", batengSecurityProperties.getBrowserProperties().getLoginPage(), batengSecurityProperties.getValidateCodeProperties().getImageCodeProperties().getUrl()).permitAll()
    //                .antMatchers("/authentication/require")
                    .anyRequest()
                    .authenticated()
                .and().csrf().disable();
    }


}
