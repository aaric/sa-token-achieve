package com.sample.satoken.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Sa-Token Auth Header Filter
 *
 * @author Aaric, created on 2022-12-26T14:17.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
@Component
public class SaTokenAuthHeaderFilter implements GlobalFilter, Ordered {

    /**
     * Bearer 令牌 Header Key
     */
    private static final String OAUTH2_AUTHORIZATION_KEY = "Authorization";

    /**
     * Access Token 令牌 Query Key
     */
    private static final String OAUTH2_ACCESS_TOKEN_KEY = "access_token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取 token 字符串
        //String token = exchange.getRequest().getHeaders().getFirst(OAUTH2_AUTHORIZATION_KEY);
        String token = exchange.getRequest().getQueryParams().getFirst(OAUTH2_ACCESS_TOKEN_KEY);
        log.debug("filter -> token={}", token);
        /*if (token == null || token.isEmpty()) { //如果token不合法，直接返回401
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }*/
        // 将令牌设置到 headers 中，记得 build
        token = token.contains("Bearer") ? token : "Bearer " + token;
        ServerHttpRequest request = exchange.getRequest().mutate().header(OAUTH2_AUTHORIZATION_KEY, token).build();
        // 将现在的 request 变成 exchange 对象
        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
