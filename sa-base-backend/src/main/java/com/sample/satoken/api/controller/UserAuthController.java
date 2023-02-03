package com.sample.satoken.api.controller;

import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.UserAuthApi;
import com.sample.satoken.service.UserAuthService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证 控制器
 *
 * @author Aaric, created on 2023-02-02T16:46.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@RequestMapping("/api/base/user/auth")
@RestController
public class UserAuthController implements UserAuthApi {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    @GetMapping("/getPermissionList")
    public SaResult getPermissionList(@ApiParam("登录ID") @RequestParam String loginId, @ApiParam("登录类型") @RequestParam String loginType) {
        return SaResult.data(userAuthService.getPermissionList(loginId, loginType));
    }

    @Override
    @GetMapping("/getRoleList")
    public SaResult getRoleList(@ApiParam("登录ID") @RequestParam String loginId, @ApiParam("登录类型") @RequestParam String loginType) {
        return SaResult.data(userAuthService.getRoleList(loginId, loginType));
    }
}
