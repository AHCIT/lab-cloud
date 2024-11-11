package com.cit.lab.order.server.service.impl;

import com.cit.lab.api.order.clientobject.OrderCO;
import com.cit.lab.order.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: Richard
 * @Description: OrderServiceImpl
 * @CreateDate: 2023/9/24 15:30
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/24 15:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    /**
     * 处理订单数据
     *
     * @param order
     */
    public void solveRetry(OrderCO order) {
        log.info("solve order: {}", order);
    }
}
