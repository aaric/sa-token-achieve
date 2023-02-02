package com.sample.satoken.api.feign;

import com.sample.satoken.api.UserExtendApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户扩展接口 Api客户端调用接口
 *
 * @author Aaric, created on 2023-02-02T14:06.
 * @version 0.10.0-SNAPSHOT
 */
@FeignClient(value = "sa-base-backend")
public interface UserExtendApiFeign extends UserExtendApi {
}
