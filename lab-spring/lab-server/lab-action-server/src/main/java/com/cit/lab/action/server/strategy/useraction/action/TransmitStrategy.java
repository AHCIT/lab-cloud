package com.cit.lab.action.server.strategy.useraction.action;


import com.cit.lab.action.server.strategy.useraction.ActionStrategy;
import com.cit.lab.api.action.dto.ActionDTO;
import com.cit.lab.api.action.enums.UserActionEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

import static com.cit.lab.action.server.constant.RedisKeyConstant.REDIS_TRANSMIT_COUNT;
import static com.cit.lab.action.server.constant.RedisKeyConstant.REDIS_TRANSMIT_STATUS;

/**
 * @Author: Richard
 * @Description: 转发策略
 * @CreateDate: 2023/4/5 20:42
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 20:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component("transmitStrategy")
public class TransmitStrategy implements ActionStrategy {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private KafkaTemplate kafkaTemplate;
    @Resource
    private RedisScript<Void> setAction;
    @Value(value = "${kafka.action.topic}")
    private String actionTopic;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean doAction(String userId, String infoId, String status) {
        Object cache = stringRedisTemplate.opsForHash().get(REDIS_TRANSMIT_STATUS + userId, infoId);
        checkStatus(status, cache);
        stringRedisTemplate.execute(setAction, Arrays.asList(REDIS_TRANSMIT_STATUS + userId, REDIS_TRANSMIT_COUNT + infoId),
                infoId, status);
        log.info("Update transmit userId {} infoId {}, status {}, count {}!", userId, infoId, status,
                stringRedisTemplate.opsForValue().get(REDIS_TRANSMIT_COUNT + infoId));
        ActionDTO actionDTO = buildActionDTO(userId, infoId, status, UserActionEnum.TRANSMIT);
        try {
            kafkaTemplate.send(actionTopic, objectMapper.writeValueAsString(actionDTO));
        } catch (Exception e) {
            log.error("fail to send transmit action with action dto: {}", actionDTO, e);
            return false;
        }
        return true;
    }

}
