package com.cit.lab.api.order.client;

import com.cit.basic.dto.PageInfo;
import com.cit.basic.dto.Result;
import com.cit.lab.api.order.clientobject.OrderCO;
import org.springframework.web.bind.annotation.*;

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
     * 测试
     */
    @GetMapping("/test")
    Result<Void> send();

    /**
     * 根据id查询
     *
     * @param id 订单id
     * @return 订单详情
     */
    @GetMapping("/{id}")
    Result<OrderCO> findById(@PathVariable("id") Long id);


    /**
     * 创建订单
     *
     * @param orderCO 订单
     */
    @PostMapping("/create")
    Result<Void> create(@RequestBody @Valid OrderCO orderCO);

    /**
     * 更新订单
     *
     * @param orderCO 订单
     */
    @PutMapping("/update")
    Result<Void> update(@RequestBody @Valid OrderCO orderCO);

    /**
     *
     */

    /**
     * 删除订单
     *
     * @param id 订单id
     */
    @DeleteMapping("/delete/{id}")
    Result<Void> deleteById(@PathVariable("id") Long id);

    /**
     * 订单分页列表
     */
    @GetMapping("/list")
    Result<PageInfo<OrderCO>> list();

}
