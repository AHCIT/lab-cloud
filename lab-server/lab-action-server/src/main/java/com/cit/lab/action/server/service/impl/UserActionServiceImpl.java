package com.cit.lab.action.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.cit.lab.action.server.dto.ActionDTO;
import com.cit.lab.action.server.exception.ConcurrentException;
import com.cit.lab.action.server.service.UserActionService;
import com.cit.lab.action.server.strategy.useraction.ActionContext;
import com.cit.lab.api.action.clientobject.ActionDetailCO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Richard
 * @Description: 用户行为数据处理业务处理实现
 * @CreateDate: 2023/4/5 20:00
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 20:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@Service
@Getter
public class UserActionServiceImpl implements UserActionService {
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisScript<String> getAction;
    private final ActionContext context;
    private final AtomicLong send = new AtomicLong();
    private final AtomicLong received = new AtomicLong();

    @Autowired
    public UserActionServiceImpl(StringRedisTemplate stringRedisTemplate, RedisScript<String> getAction, ActionContext context) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.getAction = getAction;
        this.context = context;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public ActionDetailCO doAction(String userId, String infoId, String status, Integer type) {
        boolean success = context.doStrategy(userId, infoId, status, type);
        if (!success) {
            throw new ConcurrentException("当前服务正忙，请稍后再试");
        }
        send.incrementAndGet();
        String result = stringRedisTemplate.execute(getAction, Arrays.asList(userId, infoId));
        return JSON.parseObject(result, ActionDetailCO.class);
    }

    @Override
    public void solveRetry(ActionDTO actionDTO) {
        log.info("sava action {}", JSON.toJSON(actionDTO));
        received.incrementAndGet();
    }

    @Override
    public String checkRes() {
        return "received/send:".concat(received.toString().concat("/").concat(send.toString()));
    }
}
