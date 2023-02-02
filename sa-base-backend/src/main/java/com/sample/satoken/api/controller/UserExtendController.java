package com.sample.satoken.api.controller;

import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.UserExtendApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户招展 控制器
 *
 * @author Aaric, created on 2023-02-02T14:04.
 * @version 0.1.0-SNAPSHOT
 */
@Slf4j
@RequestMapping("/api/base/user/extend")
@RestController
public class UserExtendController implements UserExtendApi {

    @Override
    @GetMapping("/getUserId")
    public SaResult getUserId() {
        return SaResult.data("fake user id");
    }
}
