package com.dayang.share4.configuration;

import com.dayang.share4.filter.LoginFilter;
import com.dayang.share4.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }

    @Bean
    FilterRegistrationBean loginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/hello.html");
        registration.setName("loginFilterRegistration");
        return registration;
    }
}