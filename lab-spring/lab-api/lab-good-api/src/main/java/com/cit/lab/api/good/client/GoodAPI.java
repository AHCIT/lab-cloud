package com.cit.lab.api.good.client;

import com.cit.basic.dto.Result;
import com.cit.lab.api.good.clientobject.GoodCO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 商品接口
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:16
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface GoodAPI {
    /**
     * 根据id查询
     *
     * @param id 商品id
     * @return 商品详情
     */
    @GetMapping("/{id}")
    Result<GoodCO> findById(@PathVariable("id") String id);
}
