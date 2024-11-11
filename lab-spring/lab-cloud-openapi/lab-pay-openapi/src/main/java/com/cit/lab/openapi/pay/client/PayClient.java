package com.cit.lab.openapi.pay.client;

import com.cit.lab.api.pay.client.PayAPI;
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
@FeignClient(value = "lab-pay-server", path = "/lab/pay", contextId = "payClient")
public interface PayClient extends PayAPI {
}
