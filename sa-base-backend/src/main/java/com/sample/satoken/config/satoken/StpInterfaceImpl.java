package com.sample.satoken.config.satoken;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> pList = new ArrayList<>();
        //SaSession session = SaSessionCustomUtil.getSessionById(StpUtil.getLoginIdAsString());
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        switch (String.valueOf(loginId)) {
            case "10001":
                List<String> list = session.get("permission-list", () -> Arrays.asList("action.delete", "action.page", "action.all"));
                pList.addAll(list);
                log.info("getPermissionList -> loginId=10001, pList={}", pList);
                break;
            case "10002":
                log.info("getPermissionList -> loginId=10002, pList={}", pList);
                break;
        }
        return pList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> rList = new ArrayList<>();
        //SaSession session = SaSessionCustomUtil.getSessionById(StpUtil.getLoginIdAsString());
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        switch (String.valueOf(loginId)) {
            case "10001":
                log.info("getRoleList -> loginId=10001, rList={}", rList);
                break;
            case "10002":
                List<String> list = session.get("role-list", () -> Arrays.asList("admin"));
                rList.addAll(list);
                log.info("getRoleList -> loginId=10002, rList={}", rList);
                break;
        }
        return rList;
    }
}
