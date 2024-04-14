package com.cit.lab.api.action.enums;

import lombok.Getter;

/**
 * @Description: ActionStrategyEnum
 * @Author: Richard
 * @CreateDate: 2024/1/20 18:51
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/1/20 18:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Getter
public enum ActionStrategyEnum {
    COLLECT(1, "collectStrategy", "收藏"),
    LIKE(2, "likeStrategy", "点赞"),
    SCAN(3, "scanStrategy", "浏览"),
    TRANSMIT(4, "transmitStrategy", "转发");

    /**
     * 支付策略code
     */
    private final Integer type;

    /**
     * bean名称
     */
    private final String beanName;

    /**
     * 信息
     */
    private final String info;

    ActionStrategyEnum(Integer type, String beanName, String info) {
        this.type = type;
        this.beanName = beanName;
        this.info = info;
    }

    /**
     * Retrieve an enum constant based on the type value.
     *
     * @param type The type value to look for.
     * @return The corresponding enum constant, or null if not found.
     */
    public static ActionStrategyEnum getByType(Integer type) {
        for (ActionStrategyEnum strategyEnum : values()) {
            if (strategyEnum.getType().equals(type)) {
                return strategyEnum;
            }
        }
        return null;
    }
}
