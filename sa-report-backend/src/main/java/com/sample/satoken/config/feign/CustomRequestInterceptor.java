package com.sample.satoken.config.feign;

import cn.dev33.satoken.context.SaHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

/**
 * 自定义 Feign 请求拦截器
 *
 * @author Aaric, created on 2023-02-02T16:08.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
public class CustomRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String token = SaHolder.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        log.debug("apply -> token={}, path={}", token, SaHolder.getRequest().getRequestPath());
        if (StringUtils.isNotBlank(token)) {
            template.header(HttpHeaders.AUTHORIZATION, token);
        }
    }
}
