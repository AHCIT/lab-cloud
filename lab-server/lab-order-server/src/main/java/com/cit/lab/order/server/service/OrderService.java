package com.cit.lab.order.server.service;

import com.cit.lab.api.order.clientobject.OrderCO;

/**
 * @Author: Richard
 * @Description: OrderService
 * @CreateDate: 2023/9/24 15:30
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/24 15:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface OrderService {
    /**
     * 处理订单数据
     *
     * @param order
     */
    void solveRetry(OrderCO order);
}
