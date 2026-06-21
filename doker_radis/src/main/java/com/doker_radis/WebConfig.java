package com.doker_radis;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RequestCountInterceptor requestCountInterceptor;

    public WebConfig(RequestCountInterceptor requestCountInterceptor) {
        this.requestCountInterceptor = requestCountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestCountInterceptor)
                .addPathPatterns("/**");
    }
}