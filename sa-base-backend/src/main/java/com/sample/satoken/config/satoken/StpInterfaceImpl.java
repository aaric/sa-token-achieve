package com.sample.satoken.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.sample.satoken.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author Aaric, created on 2022-12-02T11:18.
 * @version 0.3.0-SNAPSHOT
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserAuthService userAuthService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> pList = userAuthService.getPermissionList((String) loginId, loginType);
        log.info("getPermissionList -> loginId={}, pList={}", loginId, pList);
        return pList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> rList = userAuthService.getRoleList((String) loginId, loginType);
        log.info("getRoleList -> loginId={}, rList={}", loginId, rList);
        return rList;
    }
}
