package com.campussecondhand.config;

import com.campussecondhand.intercepter.JwtTokenUserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //TODO 增加拦截器
        registry.addInterceptor(new JwtTokenUserInterceptor())
                .addPathPatterns("/user")
                .excludePathPatterns("/user/login", "/user/register");
    }

}
