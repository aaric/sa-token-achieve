package com.sample.satoken.api.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息 控制器
 *
 * @author Aaric, created on 2022-11-30T16:09.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@RequestMapping("/default/user")
@RestController
public class UserController implements UserApi {

    @Override
    @GetMapping("/login")
    public SaResult login() {
        StpUtil.login(10001);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return SaResult.data(tokenInfo);
    }

    @Override
    @GetMapping("/logout")
    public SaResult logout() {
        StpUtil.logout(10001);
        return SaResult.ok();
    }

    @Override
    @GetMapping("/current")
    public SaResult current() {
        return SaResult.data(StpUtil.getLoginId());
    }
}
