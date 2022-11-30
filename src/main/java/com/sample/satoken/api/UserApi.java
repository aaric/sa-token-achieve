package com.sample.satoken.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户信息 Api接口
 *
 * @author Aaric, created on 2022-11-30T15:57.
 * @version 0.1.0-SNAPSHOT
 */
@Api(tags = "用户信息管理")
public interface UserApi {

    @ApiOperation("简单get请求")
    String get();
}
