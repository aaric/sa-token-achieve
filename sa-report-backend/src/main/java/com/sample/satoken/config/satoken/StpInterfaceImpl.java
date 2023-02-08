package com.sample.satoken.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.util.SaResult;
import com.sample.satoken.api.feign.UserAuthApiFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 *
 * @author Aaric, created on 2023-02-02T16:40.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserAuthApiFeign userAuthApiFeign;

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> pList = new ArrayList<>();
        SaResult saResult = userAuthApiFeign.getPermissionList((String) loginId, loginType);
        if (SaResult.CODE_SUCCESS == saResult.getCode()) {
            pList = (List<String>) saResult.getData();
        }
        log.info("getPermissionList -> loginId={}, loginType={}, pList={}", loginId, loginType, pList);
        return pList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> rList = new ArrayList<>();
        SaResult saResult = userAuthApiFeign.getRoleList((String) loginId, loginType);
        if (SaResult.CODE_SUCCESS == saResult.getCode()) {
            rList = (List<String>) saResult.getData();
        }
        log.info("getRoleList -> loginId={}, loginType={},, rList={}", loginId, loginType, rList);
        return rList;
    }
}
