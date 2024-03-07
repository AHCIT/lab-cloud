package com.cit.lab.order.server.controller;

import com.cit.basic.dto.Result;
import com.cit.lab.api.auth.clientobject.UserCO;
import com.cit.lab.api.order.client.OrderAPI;
import com.cit.lab.api.order.clientobject.OrderCO;
import com.cit.lab.openapi.auth.client.SysUserClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 订单服务
 * @Author: Richard
 * @CreateDate: 2023/9/12 21:35
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/12 21:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/lab/order")
public class OrderController implements OrderAPI {
    @Resource
    private SysUserClient sysUserClient;
    @Resource
    private KafkaTemplate kafkaTemplate;
    @Resource
    private ObjectMapper objectMapper;
    @Value(value = "${kafka.order.topic}")
    private String orderTopic;


    @Override
    public Result<OrderCO> findById(Long id) {
        Result<UserCO> userCOResult = sysUserClient.findById(id);
        UserCO userCO = userCOResult.getData();
        OrderCO orderCO = new OrderCO();
        orderCO.setUserId(userCO.getId());
        orderCO.setProductName("mac");
        orderCO.setId(id);
        return Result.ofSuccess(orderCO);
    }

    @Override
    public Result<Void> send() {
        try {
            for (long i = 0; i < 100; i++) {
                OrderCO order = new OrderCO();
                order.setProductName("product" + i);
                order.setId(i);
                kafkaTemplate.send(orderTopic, objectMapper.writeValueAsString(order));
                log.info("order: {}", order);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Result.ofSuccess();
    }

    @Override
    public Result<Void> create(OrderCO orderCO) {
        return Result.ofSuccess(null, "创建订单成功");
    }
}
