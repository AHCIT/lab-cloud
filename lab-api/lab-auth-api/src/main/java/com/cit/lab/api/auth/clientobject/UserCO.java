package com.cit.lab.api.auth.clientobject;

import com.cit.basic.dto.BaseCO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 用户信息
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:09
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCO extends BaseCO {
    private String userName;
}
