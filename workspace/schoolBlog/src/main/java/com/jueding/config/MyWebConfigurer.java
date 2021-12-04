package com.jueding.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 北落燕门
 */
@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都可跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/api/ file/**")
//                .addResourceLocations("file:" + "F:/code/workspace/img/");
//    }

}
