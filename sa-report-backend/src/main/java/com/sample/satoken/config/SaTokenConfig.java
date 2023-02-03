package com.sample.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 配置
 *
 * @author Aaric, created on 2022-12-02T10:17.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(auth -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico", "/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs", "/error");
    }
}
