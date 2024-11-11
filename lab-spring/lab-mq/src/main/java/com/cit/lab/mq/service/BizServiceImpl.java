package com.cit.lab.mq.service;

import com.alibaba.fastjson.JSON;
import com.cit.lab.api.action.dto.ActionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Description: BizServiceImpl
 * @Author: Richard
 * @CreateDate: 2024/4/14 22:01
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/4/14 22:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Service
public class BizServiceImpl implements BizService {
    @Override
    public void solveRetry(ActionDTO actionDTO) {
        log.info("sava action {}", JSON.toJSON(actionDTO));
    }
}
