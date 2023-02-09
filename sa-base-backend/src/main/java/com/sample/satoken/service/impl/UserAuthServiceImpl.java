package com.sample.satoken.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.sample.satoken.service.UserAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户认证服务接口实现
 *
 * @author Aaric, created on 2023-02-02T16:50.
 * @version 0.10.0-SNAPSHOT
 */
@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Override
    public List<String> getPermissionList(String loginId, String loginType) {
        List<String> pList = new ArrayList<>();
        //SaSession session = SaSessionCustomUtil.getSessionById(StpUtil.getLoginIdAsString());
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        switch (String.valueOf(loginId)) {
            case "10001":
                List<String> list = session.get("permission-list", () -> Arrays.asList("action.delete",
                        "action.page", "action.all", "report.data"));
                pList.addAll(list);
                break;
            case "10002":
                break;
            default:
        }
        return pList;
    }

    @Override
    public List<String> getRoleList(String loginId, String loginType) {
        List<String> rList = new ArrayList<>();
        //SaSession session = SaSessionCustomUtil.getSessionById(StpUtil.getLoginIdAsString());
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        switch (String.valueOf(loginId)) {
            case "10001":
                break;
            case "10002":
                List<String> list = session.get("role-list", () -> Arrays.asList("admin"));
                rList.addAll(list);
                break;
            default:
        }
        return rList;
    }
}
