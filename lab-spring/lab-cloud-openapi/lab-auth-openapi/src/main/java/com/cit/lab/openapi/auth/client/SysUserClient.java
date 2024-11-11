package com.cit.lab.openapi.auth.client;

import com.cit.lab.api.auth.client.SysUserAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 用户服务开放接口
 * @Author: Richard
 * @CreateDate: 2023/9/12 21:16
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/12 21:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@FeignClient(value = "lab-auth-server", path = "/lab/auth/user", contextId = "sysUserClient")
public interface SysUserClient extends SysUserAPI {
}
