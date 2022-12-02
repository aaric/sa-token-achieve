package com.sample.satoken.api;

import cn.dev33.satoken.util.SaResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户动作 Api接口
 *
 * @author Aaric, created on 2022-12-02T10:31.
 * @version 0.3.0-SNAPSHOT
 */
@Api(tags = "用户动作管理")
public interface UserActionApi {

    @ApiOperation("新增操作")
    SaResult add();

    @ApiOperation("删除操作")
    SaResult delete();

    @ApiOperation("更新操作")
    SaResult update();

    @ApiOperation("分页操作")
    SaResult page();
}
