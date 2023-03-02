package com.sample.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Sa-Token 配置
 *
 * @author Aaric, created on 2022-12-02T10:17.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 测试令牌字符串
     */
    public static final String TEST_TOKEN_VALUE = "oV_cdI21l5DyeUu3e_zlmAp0ln5d8vuvbW__";

    @Autowired
    protected MessageSource messageSource;

    @Bean
    @ConditionalOnMissingBean(LocaleResolver.class)
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册本地化拦截器，启用多语言支持
        registry.addInterceptor(new LocaleChangeInterceptor());

        // 注册 Sa-Token 拦截器，启用注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(auth -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico", "/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs", "/error")
                .excludePathPatterns("/api/base/user/login")
                .excludePathPatterns("/api/base/user/httpBasic");
    }

    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }
}
