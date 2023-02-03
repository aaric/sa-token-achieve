package com.sample.satoken.api;

import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户数据 Api接口
 *
 * @author Aaric, created on 2023-02-02T15:18.
 * @version 0.10.0-SNAPSHOT
 */
@Api(tags = "用户数据接口")
public interface ReportApi {

    @ApiOperation("数据")
    SaResult data();

    @ApiOperation("远程数据")
    SaResult remote();
}
