package com.sample.satoken.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaCheckSafe;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.UserActionApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户动作 控制器
 *
 * @author Aaric, created on 2022-12-02T10:33.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@RequestMapping("/api/default/user/action")
@RestController
public class UserActionController implements UserActionApi {

    @Override
    @GetMapping("/add")
    @SaCheckPermission(value = {"action.add", "action.all"}, mode = SaMode.OR)
    public SaResult add() {
        log.info("add action...");
        return SaResult.ok("add");
    }

    @Override
    @GetMapping("/delete")
    @SaCheckSafe("action")
    @SaCheckPermission(value = "action.delete")
    public SaResult delete() {
        log.info("delete action...");
        return SaResult.ok("delete");
    }

    @Override
    @GetMapping("/update")
    @SaCheckPermission(value = "action.update", orRole = "admin")
    public SaResult update() {
        log.info("update action...");
        return SaResult.ok("update");
    }

    @Override
    @GetMapping("/page")
    @SaCheckPermission(value = {"action.page", "action.all"}, orRole = "admin", mode = SaMode.OR)
    public SaResult page() {
        log.info("page action...");
        return SaResult.ok("page");
    }

    @Override
    @GetMapping("/settings")
    @SaCheckRole("admin")
    public SaResult settings() {
        return SaResult.ok("settings");
    }
}
