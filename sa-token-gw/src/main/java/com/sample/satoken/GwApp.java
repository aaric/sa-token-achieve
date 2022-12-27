package com.sample.satoken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Gateway App
 *
 * @author Aaric, created on 2022-12-26T11:20.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class GwApp {

    /**
     * 主函数
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        SpringApplication.run(GwApp.class, args);
    }
}
