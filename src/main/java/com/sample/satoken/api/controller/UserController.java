package com.sample.satoken.api.controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        // isLastingCookie: 记住我
//        StpUtil.login(10001, true);
        // timeout: 有效期
        StpUtil.login(10001, new SaLoginModel()
                .setDevice("PC")
                .setTimeout(60 * 60 * 24 * 7)
                .setIsLastingCookie(true));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return SaResult.data(tokenInfo);
    }

    @Override
    @GetMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    @Override
    @GetMapping("/current")
    public SaResult current() {
        return SaResult.data(StpUtil.getLoginId());
    }

    @Override
    @GetMapping("/safeActionDo")
    public SaResult safeActionDo() {
        if (!StpUtil.isSafe("action")) {
            return SaResult.error("该操作需要二级认证");
        }

        // TODO 业务逻辑

        return SaResult.ok("已执行");
    }

    @Override
    @GetMapping("/safeActionValid")
    public SaResult safeActionValid(@RequestParam String confirmPassword) {
        if ("123456".equals(confirmPassword)) {
            StpUtil.openSafe("action", 120);
            return SaResult.ok("二级认证完成");
        }
        return SaResult.error("二级认证朱失败");
    }
}
