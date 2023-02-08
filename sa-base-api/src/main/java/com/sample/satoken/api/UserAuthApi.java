package com.sample.satoken.api;

import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户认证 Api接口
 *
 * @author Aaric, created on 2023-02-02T16:42.
 * @version 0.10.0-SNAPSHOT
 */
@Api(tags = "用户认证接口")
public interface UserAuthApi {

    @ApiOperation("获取用户权限列表")
    @GetMapping("/api/base/user/auth/getPermissionList")
    SaResult getPermissionList(@ApiParam("登录ID") @RequestParam String loginId,
                               @ApiParam("登录类型") @RequestParam String loginType);

    @ApiOperation("获取用户角色列表")
    @GetMapping("/api/base/user/auth/getRoleList")
    SaResult getRoleList(@ApiParam("登录ID") @RequestParam String loginId,
                         @ApiParam("登录类型") @RequestParam String loginType);
}
