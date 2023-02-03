package com.sample.satoken.api.feign;

import com.sample.satoken.api.UserAuthApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户认证 Api接口调用客户端
 *
 * @author Aaric, created on 2023-02-02T16:56.
 * @version 0.10.0-SNAPSHOT
 */
@FeignClient(value = "sa-base-backend")
public interface UserAuthApiFeign extends UserAuthApi {
}
