package com.cit.lab.auth.server.controller;

import com.cit.basic.dto.Result;
import com.cit.lab.api.auth.client.SysUserAPI;
import com.cit.lab.api.auth.clientobject.UserCO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 系统用户
 * @Author: Richard
 * @CreateDate: 2023/9/12 20:59
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/12 20:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/lab/auth/user")
public class SysUserController implements SysUserAPI {
    @Override
    public Result<UserCO> findById(Long id) {
        log.info("find user by id {}", id);
        UserCO userCO = new UserCO();
        userCO.setId(id);
        userCO.setDelFlag(0);
        userCO.setInputTime(System.currentTimeMillis());
        return Result.ofSuccess(userCO);
    }
}
