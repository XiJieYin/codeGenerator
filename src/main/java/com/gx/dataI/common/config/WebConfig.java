package com.gx.dataI.common.config;

import com.gx.dataI.common.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myFilter());
        //拦截/*的访问 多级匹配（springboot 过滤器/*以及匹配 /**多级匹配）
        registration.addUrlPatterns("/*");
        registration.setName("LoginFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "myFilter")
    public LoginFilter myFilter() {
        return new LoginFilter();
    }
}