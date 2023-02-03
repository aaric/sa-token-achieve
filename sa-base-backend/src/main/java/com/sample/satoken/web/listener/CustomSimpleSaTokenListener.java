package com.sample.satoken.web.listener;

import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import lombok.extern.slf4j.Slf4j;

/**
 * Sa-Token 监听器
 *
 * @author Aaric, created on 2022-12-01T16:32.
 * @version 0.2.0-SNAPSHOT
 */
@Slf4j
public class CustomSimpleSaTokenListener extends SaTokenListenerForSimple {

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        log.info("{} has logged out", loginId);
    }
}
