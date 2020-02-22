package com.xlian.system.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
class WebConfig implements WebMvcConfigurer {

    @Value("${static-resource.path}")
    String staticResourcePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/fe/**").addResourceLocations("file:///" + staticResourcePath);
    }

}