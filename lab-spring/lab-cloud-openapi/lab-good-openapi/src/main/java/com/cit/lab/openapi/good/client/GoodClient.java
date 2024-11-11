package com.cit.lab.openapi.good.client;

import com.cit.lab.api.good.client.GoodAPI;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description: 商品管理
 * @Author: Richard
 * @CreateDate: 2024/1/27 16:04
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/1/27 16:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@FeignClient(value = "lab-good-server", path = "/lab/good", contextId = "goodClient")
public interface GoodClient extends GoodAPI {
}
