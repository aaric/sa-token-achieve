package com.sample.satoken.api;

import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 用户信息 Api接口
 *
 * @author Aaric, created on 2022-11-30T15:57.
 * @version 0.1.0-SNAPSHOT
 */
@Api(tags = "用户信息管理")
public interface UserApi {

    @ApiOperation("用户登录")
    SaResult login(@ApiParam(value = "登录ID", example = "10001") int loginId);

    @ApiOperation("用户登出")
    SaResult logout();

    @ApiOperation("用户踢出")
    SaResult kickout(@ApiParam(value = "登录ID", example = "10001") int loginId);

    @ApiOperation("用户登录信息")
    SaResult current();

    @ApiOperation("二级认证操作")
    SaResult safeActionDo();

    @ApiOperation("二级认证校验")
    SaResult safeActionValid(@ApiParam(value = "确认密码", example = "123456") String confirmPassword);

    @ApiOperation("Http Basic 认证")
    SaResult httpBasic();
}
