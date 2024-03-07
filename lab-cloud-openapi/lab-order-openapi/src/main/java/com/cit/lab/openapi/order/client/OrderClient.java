package com.cit.lab.openapi.order.client;

import com.cit.lab.api.order.client.OrderAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 订单服务开放接口
 * @Author: Richard
 * @CreateDate: 2023/9/12 21:16
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/12 21:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@FeignClient(value = "lab-order-server", path = "/lab/order", contextId = "orderClient")
public interface OrderClient extends OrderAPI {
}
