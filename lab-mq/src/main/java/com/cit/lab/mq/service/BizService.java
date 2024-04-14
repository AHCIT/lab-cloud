package com.cit.lab.mq.service;

import com.cit.lab.api.action.dto.ActionDTO;

/**
 * @Description: BizService
 * @Author: Richard
 * @CreateDate: 2024/4/14 22:01
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/4/14 22:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface BizService {
    void solveRetry(ActionDTO actionDTO);
}
