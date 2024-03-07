package com.cit.lab.action.server.strategy.useraction;

import com.cit.lab.action.server.dto.ActionDTO;
import com.cit.lab.action.server.enums.UserActionEnum;
import com.cit.lab.action.server.exception.DuplicateException;
import com.cit.lab.action.server.exception.ParamException;

/**
 * @Author: Richard
 * @Description: 行为处理策略
 * @CreateDate: 2023/4/5 00:44
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 00:44
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ActionStrategy {
    default void checkStatus(String status, Object cache) {
        if (cache == null && "0".equals(status)) {
            throw new ParamException("参数异常");
        }
        if (cache != null && status.equals(String.valueOf(cache))) {
            throw new DuplicateException("重复操作!");
        }
    }

    default void checkScanStatus(String status, Object cache) {
        if ("0".equals(status)) {
            if (cache == null) {
                throw new ParamException("参数异常");
            }
            if (status.equals(String.valueOf(cache))) {
                throw new DuplicateException("重复操作!");
            }
        }
    }

    default ActionDTO buildActionDTO(String userId, String infoId, String status, UserActionEnum actionEnum) {
        return ActionDTO.builder()
                .id(Math.abs(userId.hashCode()))
                .userId(userId)
                .infoId(infoId)
                .status(status)
                .actionEnum(actionEnum)
                .build();
    }

    boolean doAction(String userId, String infoId, String status);
}
