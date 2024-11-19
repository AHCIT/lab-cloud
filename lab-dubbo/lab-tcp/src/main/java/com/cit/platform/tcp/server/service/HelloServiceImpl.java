package com.cit.platform.tcp.server.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Author: Richard
 * @Description: 测试服务接口实现
 * @CreateDate: 2024/11/19 17:34
 * @UpdateUser: zhouli
 * @UpdateDate: 2024/11/19 17:34
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@DubboService
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "";
    }
}
