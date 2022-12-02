package com.sample.satoken.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
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
        switch (String.valueOf(loginId)) {
            case "10001":
                pList.addAll(Arrays.asList("action.delete", "action.page", "action.all"));
                log.info("10001 pList: {}", pList);
                break;
            case "10002":
                log.info("10002 pList: {}", pList);
                break;
        }
        return pList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> rList = new ArrayList<>();
        switch (String.valueOf(loginId)) {
            case "10001":
                log.info("10001 rList: {}", rList);
                break;
            case "10002":
                rList.add("admin");
                log.info("10002 rList: {}", rList);
                break;
        }
        return rList;
    }
}
