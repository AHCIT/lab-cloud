package com.cit.lab.action.server.strategy.useraction.action;


import com.cit.lab.action.server.dto.ActionDTO;
import com.cit.lab.action.server.enums.UserActionEnum;
import com.cit.lab.action.server.strategy.useraction.ActionStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

import static com.cit.lab.action.server.constant.RedisKeyConstant.REDIS_COLLECT_COUNT;
import static com.cit.lab.action.server.constant.RedisKeyConstant.REDIS_COLLECT_STATUS;

/**
 * @Author: Richard
 * @Description: 收藏策略实现
 * @CreateDate: 2023/4/5 20:42
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 20:42
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Component("collectStrategy")
public class CollectStrategy implements ActionStrategy {
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
        Object cache = stringRedisTemplate.opsForHash().get(REDIS_COLLECT_STATUS + userId, infoId);
        checkStatus(status, cache);
        stringRedisTemplate.execute(setAction, Arrays.asList(REDIS_COLLECT_STATUS + userId, REDIS_COLLECT_COUNT + infoId),
                infoId, status);
        log.info("Update collect userId {} infoId {}, status {}, count {}!", userId, infoId, status,
                stringRedisTemplate.opsForValue().get(REDIS_COLLECT_COUNT + infoId));
        ActionDTO actionDTO = buildActionDTO(userId, infoId, status, UserActionEnum.COLLECT);
        try {
            kafkaTemplate.send(actionTopic, objectMapper.writeValueAsString(actionDTO));
        } catch (JsonProcessingException e) {
            log.error("fail to send collect action with action dto: {}", actionDTO, e);
            return false;
        }
        return true;
    }
}
