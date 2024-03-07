package com.cit.lab.api.auth.client;

import com.cit.basic.dto.Result;
import com.cit.lab.api.auth.clientobject.UserCO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 用户接口
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:23
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface SysUserAPI {
    /**
     * 根据id查询
     *
     * @param id 用户id
     * @return 用户详情
     */
    @GetMapping("/{id}")
    Result<UserCO> findById(@PathVariable("id") Long id);
}
