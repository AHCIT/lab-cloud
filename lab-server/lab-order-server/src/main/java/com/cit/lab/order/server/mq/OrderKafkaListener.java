package com.cit.lab.order.server.mq;


import com.cit.lab.api.order.clientobject.OrderCO;
import com.cit.lab.order.server.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Richard
 * @Description: 订单消费者
 * @CreateDate: 2023/9/24 14:17
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/24 14:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
@Slf4j
@ConfigurationProperties(prefix = "kafka.order")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderKafkaListener extends AbstractConsumerSeekAware {

    @Resource
    private OrderService orderService;
    @Resource
    private ObjectMapper objectMapper;
    /**
     * 业务名称
     */
    private String bizName;
    /**
     * 顺序消费并发级别
     */
    private Integer concurrentSize;

    /**
     * order业务顺序消费池
     */
    private KafkaConsumerPool<OrderCO> kafkaConsumerPool;

    /**
     * 初始化顺序消费池
     */
    @PostConstruct
    public void init() {
        KafkaSortConsumerConfig<OrderCO> config = new KafkaSortConsumerConfig<>();
        config.setBizName(bizName);
        config.setBizService(orderService::solveRetry);
        config.setConcurrentSize(concurrentSize);
        kafkaConsumerPool = new KafkaConsumerPool<>(config);
    }

    @KafkaListener(topics = {"${kafka.order.topic}"}, containerFactory = "orderCommonFactory")
    public void consumerMsg(List<ConsumerRecord<?, ?>> records, Acknowledgment ack) {
        if (records.isEmpty()) {
            return;
        }

        records.forEach(consumerRecord -> {
            try {
                OrderCO orderCO = objectMapper.readValue(consumerRecord.value().toString(), OrderCO.class);
                kafkaConsumerPool.submitTask(orderCO.getId(), orderCO);
            } catch (JsonProcessingException e) {
                log.error("fail to consume record {}", consumerRecord, e);
            }
        });

        // 当线程池中任务处理完成的计数达到拉取到的记录数时提交
        // 注意这里如果存在部分业务阻塞时间很长，会导致位移提交不上去，务必做好一些熔断措施
        while (true) {
            if (records.size() == kafkaConsumerPool.getPendingOffsets().get()) {
                ack.acknowledge();
                log.info("consumer offset submit: {}", records.get(records.size() - 1).offset());
                kafkaConsumerPool.getPendingOffsets().set(0L);
                break;
            }
        }
    }

}
