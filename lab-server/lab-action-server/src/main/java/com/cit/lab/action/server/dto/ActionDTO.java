package com.cit.lab.action.server.dto;

import com.cit.lab.action.server.enums.UserActionEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: ActionDTO
 * @Author: Richard
 * @CreateDate: 2024/1/20 18:20
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/1/20 18:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@Builder
public class ActionDTO implements Serializable {
    private Integer id;
    private String userId;
    private String infoId;
    private String status;
    private UserActionEnum actionEnum;
}
