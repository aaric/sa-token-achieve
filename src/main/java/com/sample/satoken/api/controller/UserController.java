package com.sample.satoken.api.controller;

import cn.dev33.satoken.basic.SaBasicUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.UserApi;
import com.sample.satoken.config.SaTokenConfig;
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
@RequestMapping("/api/default/user")
@RestController
public class UserController implements UserApi {

    @Override
    @GetMapping("/login")
    public SaResult login() {
        int loginId = 10001;
        if (StpUtil.isDisable(loginId, "action")) {
            log.info("disable seconds: {}", StpUtil.getDisableTime(loginId, "action"));
        }
        StpUtil.checkDisable(loginId, "action");

        // isLastingCookie: 记住我
//        StpUtil.login(10001, true);
        // timeout: 有效期
        StpUtil.login(loginId, new SaLoginModel()
                .setDevice("PC")
                .setToken(SaTokenConfig.TEST_TOKEN_VALUE)
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
    @GetMapping("/kickout")
    public SaResult kickout() {
        StpUtil.kickout(10001);
        // 封禁用户1天
        StpUtil.disable(10001, "action", 86400);
        return SaResult.ok();
    }

    @Override
    @GetMapping("/current")
    public SaResult current() {
        log.info("sa-token: {}", StpUtil.getTokenValue());
        return SaResult.data(StpUtil.getLoginId());
    }

    @Override
    @GetMapping("/safeActionDo")
    public SaResult safeActionDo() {
        if (!StpUtil.isSafe("action")) {
            return SaResult.error("require second auth");
        }

        // TODO other

        return SaResult.ok("ok");
    }

    @Override
    @GetMapping("/safeActionValid")
    public SaResult safeActionValid(@RequestParam String confirmPassword) {
        if ("123456".equals(confirmPassword)) {
            StpUtil.openSafe("action", 120);
            return SaResult.ok("second auth ok");
        }
        return SaResult.error("second auth error");
    }

    @Override
    @GetMapping("/httpBasic")
    public SaResult httpBasic() {
        // header -> Authorization: Basic c2E6MTIzNDU2
        SaBasicUtil.check("sa:123456");

        // TODO other

        return SaResult.ok("ok");
    }
}
