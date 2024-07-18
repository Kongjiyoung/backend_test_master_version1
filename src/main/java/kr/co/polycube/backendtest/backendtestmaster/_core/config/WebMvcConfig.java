package kr.co.polycube.backendtest.backendtestmaster._core.config;

import kr.co.polycube.backendtest.backendtestmaster._core.filter.URLFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // IoC
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<URLFilter> corsFilter(){
        FilterRegistrationBean<URLFilter> bean = new FilterRegistrationBean<>(new URLFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}