package com.sample.satoken;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.listener.SaTokenEventCenter;
import cn.dev33.satoken.listener.SaTokenListenerForSimple;
import com.sample.satoken.web.listener.CustomSaTokenListener;
import com.sample.satoken.web.listener.CustomSimpleSaTokenListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Base App
 *
 * @author Aaric, created on 2022-11-30T15:51.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class SaTokenApp {

    /**
     * 主函数
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        SpringApplication.run(SaTokenApp.class, args);

        log.info("{}", SaManager.getConfig());
        SaTokenEventCenter.registerListener(new CustomSaTokenListener());
        SaTokenEventCenter.registerListener(new CustomSimpleSaTokenListener());
        SaTokenEventCenter.registerListener(new SaTokenListenerForSimple() {

            @Override
            public void doKickout(String loginType, Object loginId, String tokenValue) {
                log.info("{} has kicked out", loginId);
            }
        });
    }
}
