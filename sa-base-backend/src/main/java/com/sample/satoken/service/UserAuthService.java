package com.sample.satoken.service;

import java.util.List;

/**
 * 用户认证服务接口
 *
 * @author Aaric, created on 2023-02-02T16:49.
 * @version 0.10.0-SNAPSHOT
 */
public interface UserAuthService {

    /**
     * 获取用户权限列表
     *
     * @param loginId   登录ID
     * @param loginType 登录类型
     * @return
     */
    List<String> getPermissionList(String loginId, String loginType);

    /**
     * 获取用户角色列表
     *
     * @param loginId   登录ID
     * @param loginType 登录类型
     * @return
     */
    List<String> getRoleList(String loginId, String loginType);

    /**
     * 刷新对应loginId权限与角色列表
     *
     * @param loginId 登录ID
     */
    void refreshPermAndRole(String loginId);
}
