package com.sample.satoken.api.controller;

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
    @GetMapping("/get")
    public String get() {
        return "hello world";
    }
}
