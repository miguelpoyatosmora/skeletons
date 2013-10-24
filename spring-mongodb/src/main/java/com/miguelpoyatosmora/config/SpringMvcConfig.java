package com.miguelpoyatosmora.config;


import com.miguelpoyatosmora.controller.EventController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = {EventController.class})
@EnableWebMvc
public class SpringMvcConfig{}/* extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        httpMessageConverters.add(new EventMessageConverter(MediaType.APPLICATION_JSON));
    }
}                               */