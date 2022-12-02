package com.sample.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 配置
 *
 * @author Aaric, created on 2022-12-02T10:17.
 * @version 0.3.0-SNAPSHOT
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 测试令牌字符串
     */
    public static final String TEST_TOKEN_VALUE = "oV_cdI21l5DyeUu3e_zlmAp0ln5d8vuvbW__";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico", "/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs")
                .excludePathPatterns("/api/default/user/login");
    }
}
