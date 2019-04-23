package com.sic777.restful.spring.config;

import com.sic777.restful.spring.filters.HttpServletRequestWrapperFilter;
import com.sic777.restful.spring.filters.RequestMethodFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean requestMethodFilterRegister() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new RequestMethodFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }


    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilterRegister() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new HttpServletRequestWrapperFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
