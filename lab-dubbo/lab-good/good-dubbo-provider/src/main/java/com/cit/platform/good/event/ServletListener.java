package com.cit.platform.good.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @Author: Richard
 * @Description: ServletListener
 * @CreateDate: 2024/11/20 19:39
 * @UpdateUser: zhouli
 * @UpdateDate: 2024/11/20 19:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

@Component
public class ServletListener implements ApplicationListener<ServletRequestHandledEvent> {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        Throwable failureCause = event.getFailureCause();
        if (failureCause != null) {
            logger.warn("错误原因: {}", failureCause.getMessage());
        }
        // 比如我这里只记录接口响应时间大于1秒的日志
        logger.warn("请求客户端地址：{}, 请求URL: {}, 请求Method: {}, 请求耗时:{} ms",
                event.getClientAddress(),
                event.getRequestUrl(),
                event.getMethod(),
                event.getProcessingTimeMillis());

    }
}
