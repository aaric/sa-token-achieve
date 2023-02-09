package com.sample.satoken.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.ReportApi;
import com.sample.satoken.api.feign.UserExtendApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户数据 控制器
 *
 * @author Aaric, created on 2023-02-02T15:20.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@RequestMapping("/api/report/report")
@RestController
public class ReportController implements ReportApi {

    @Autowired
    private UserExtendApiFeign userExtendApiFeign;

    @Override
    @GetMapping("/data")
    @SaCheckPermission("report.data")
    public SaResult data() {
        return SaResult.data("test content");
    }

    @Override
    @GetMapping("/remote")
    @SaCheckPermission("report.analysis")
    public SaResult remote() {
        SaResult saResult = userExtendApiFeign.getUserId();
        if (SaResult.CODE_SUCCESS != saResult.getCode()) {
            throw new IllegalArgumentException("feign request error");
        }
        String userId = (String) saResult.getData();
        log.info("data -> userId={}", userId);
        return SaResult.data(userId);
    }
}
