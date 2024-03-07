package com.cit.lab.api.order.client;

import com.cit.basic.dto.Result;
import com.cit.lab.api.order.clientobject.OrderCO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @Description: 订单接口
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:16
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface OrderAPI {
    /**
     * 根据id查询
     *
     * @param id 订单id
     * @return 订单详情
     */
    @GetMapping("/{id}")
    Result<OrderCO> findById(@PathVariable("id") Long id);

    @GetMapping("/test")
    Result<Void> send();

    @PostMapping("/create")
    Result<Void> create(@RequestBody @Valid OrderCO orderCO);
}
