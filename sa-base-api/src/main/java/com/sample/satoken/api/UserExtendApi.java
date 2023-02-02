package com.sample.satoken.api;

import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户扩展接口 Api接口
 *
 * @author Aaric, created on 2023-02-02T13:53.
 * @version 0.10.0-SNAPSHOT
 */
@Api(tags = "用户扩展接口")
public interface UserExtendApi {

    @ApiOperation("获取用户ID")
    SaResult getUserId();
}
