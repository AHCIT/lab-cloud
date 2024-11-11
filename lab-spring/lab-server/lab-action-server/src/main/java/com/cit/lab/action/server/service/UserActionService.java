package com.cit.lab.action.server.service;

import com.cit.lab.api.action.clientobject.ActionDetailCO;

/**
 * @Author: Richard
 * @Description: 用户行为数据处理接口
 * @CreateDate: 2023/4/5 19:59
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 19:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserActionService {
    ActionDetailCO doAction(String userId, String infoId, String status, Integer type);
}
