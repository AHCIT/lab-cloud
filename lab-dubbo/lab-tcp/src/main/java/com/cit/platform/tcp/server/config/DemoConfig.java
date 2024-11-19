package com.cit.platform.tcp.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Richard
 * @Description: 配置读取验证
 * @CreateDate: 2024/11/19 17:14
 * @UpdateUser: zhouli
 * @UpdateDate: 2024/11/19 17:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "demo.tcp")
public class DemoConfig {
    private Integer port;
}
