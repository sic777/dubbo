package com.sic777.restful.springboot.config;

import com.sic777.restful.springboot.filters.RequestMethodFilter;
import com.sic777.utils.proguard.NoProguard;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoProguard
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegister() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        frBean.setFilter(new RequestMethodFilter());
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
