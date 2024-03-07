package com.cit.lab.api.order.clientobject;

import com.cit.basic.dto.BaseCO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Description: 订单信息
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:11
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderCO extends BaseCO {
    private Long userId;
    private String productName;
}
