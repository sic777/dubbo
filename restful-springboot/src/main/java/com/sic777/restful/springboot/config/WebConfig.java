package com.sic777.restful.springboot.config;

import com.sic777.restful.springboot.interceptor.AuthInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.sic777.common.utils.proguard.NoProguard;
import com.sic777.restful.springboot.interceptor.SignInterceptor;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@NoProguard
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(this.signInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //需要扩展Json格式化请在数组内添加
        SerializerFeature[] serializerFeatures = {
                SerializerFeature.PrettyFormat
        };
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }


    @Bean
    public SignInterceptor signInterceptor() {
        return new SignInterceptor();
    }
}
