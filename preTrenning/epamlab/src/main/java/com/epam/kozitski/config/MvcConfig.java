package com.epam.kozitski.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    private static final String TEMPLATE_SUFFIX = ".ftl";
    private static final String VIEW_PATH = "/WEB-INF/views/";

    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver freeMarker = new FreeMarkerViewResolver();

        freeMarker.setOrder(1);
        freeMarker.setSuffix(TEMPLATE_SUFFIX);
        freeMarker.setPrefix(VIEW_PATH);

        return freeMarker;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPaths("/", VIEW_PATH);
        return freeMarkerConfigurer;
    }

}
