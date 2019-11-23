package com.parkson.assignment.config;

import com.parkson.assignment.service.AuthenticationSuccessHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // TODO Auto-generated method stub
    return super.authenticationManagerBean();
  }

  @Bean
  public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
    return new AuthenticationSuccessHandlerImpl();
  }

  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/", "/login", "/registerUser", "/loginUser")
        .permitAll()
        .antMatchers("/user/dashboard","/company/add")
        .hasAnyAuthority("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginProcessingUrl("/loginUser")
        .successHandler(myAuthenticationSuccessHandler())
        .and()
        .csrf()
        .disable();
  }
}
