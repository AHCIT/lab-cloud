package com.cit.lab.action.server.strategy.useraction;

import com.cit.lab.action.server.enums.ActionStrategyEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: Richard
 * @Description: 行为策略工厂
 * @CreateDate: 2023/4/5 00:58
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 00:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component
public class ActionStrategyFactory {

    /**
     * 通过Spring容器的方式注入
     */
    @Resource
    private Map<String, ActionStrategy> actionStrategyMap;

    /**
     * 获取对应行为策略类
     *
     * @param actionStrategyEnum 行为策略枚举
     */
    public ActionStrategy getActionStrategy(ActionStrategyEnum actionStrategyEnum) {

        if (!actionStrategyMap.containsKey(actionStrategyEnum.getBeanName())) {
            log.info("没有对应的行为策略，无法进行操作");
            return null;
        }

        return actionStrategyMap.get(actionStrategyEnum.getBeanName());
    }
}
