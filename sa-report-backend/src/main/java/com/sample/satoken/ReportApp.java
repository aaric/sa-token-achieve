package com.sample.satoken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 报表模块应用
 *
 * @author Aaric, created on 2023-02-02T15:15.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients
public class ReportApp {

    /**
     * 主函数
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        SpringApplication.run(ReportApp.class, args);
    }
}
