package com.sample.satoken.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

/**
 * Sa-Token 过滤器配置（无法设置支持注解，不推荐）
 *
 * @author Aaric, created on 2022-12-02T11:44.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
//@Configuration
public class SaTokenFilterConfig {

    @Bean
    public SaServletFilter saServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico", "/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs")
                .addExclude("/api/default/user/httpBasic")
                .setAuth(auth -> {
                    SaRouter.match("/**", "/api/default/user/login", () -> StpUtil.checkLogin());
                }).setError(e -> {
                    log.error("auth exception", e);
                    SaResult result = SaResult.error(e.getMessage());
                    if (e instanceof NotLoginException) {
                        result = SaResult.code(403).setMsg("not login");
                    }
                    return result;
                });
    }
}
