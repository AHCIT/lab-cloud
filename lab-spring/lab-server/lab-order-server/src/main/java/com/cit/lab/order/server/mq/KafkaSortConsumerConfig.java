package com.cit.lab.order.server.mq;

import lombok.Data;

import java.util.function.Consumer;

/**
 * @Author: Richard
 * @Description: kafka顺序消费线程池配置参数
 * @CreateDate: 2023/9/24 14:16
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/24 14:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class KafkaSortConsumerConfig<E> {

    /**
     * 业务名称
     */
    String bizName;

    /**
     * 并发级别，多少的队列与线程处理任务
     */
    Integer concurrentSize;

    /**
     * 业务处理服务
     */
    Consumer<E> bizService;

}
