package com.cit.lab.action.server.strategy.useraction;

import com.cit.lab.action.server.enums.ActionStrategyEnum;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: Richard
 * @Description: 点赞数据处理策略
 * @CreateDate: 2023/4/5 01:10
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 01:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Builder
@Component
@Slf4j
public class ActionContext {

    @Resource
    private ActionStrategyFactory actionStrategyFactory;


    public boolean doStrategy(String userId, String infoId, String status, Integer type) {
        ActionStrategyEnum actionStrategyEnum = ActionStrategyEnum.getByType(type);
        if (Objects.isNull(actionStrategyEnum)) {
            log.info("cannot find matched action strategy with type {}", type);
            return false;
        }
        ActionStrategy actionStrategy = actionStrategyFactory.getActionStrategy(actionStrategyEnum);
        return actionStrategy.doAction(userId, infoId, status);
    }
}
