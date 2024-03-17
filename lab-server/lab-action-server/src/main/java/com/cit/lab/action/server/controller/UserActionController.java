package com.cit.lab.action.server.controller;

import com.alibaba.fastjson.JSON;
import com.cit.basic.dto.Result;
import com.cit.lab.action.server.exception.ConcurrentException;
import com.cit.lab.action.server.exception.DuplicateException;
import com.cit.lab.action.server.exception.ParamException;
import com.cit.lab.action.server.service.UserActionService;
import com.cit.lab.api.action.client.UserActionAPI;
import com.cit.lab.api.action.clientobject.ActionDetailCO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Author: Richard
 * @Description: UserActionController
 * @CreateDate: 2023/4/3 22:54
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/3 22:54
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/lab/action")
public class UserActionController implements UserActionAPI {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisScript<String> getUserAction;
    @Resource
    private UserActionService userActionService;
    private final AtomicInteger integer = new AtomicInteger();

    @GetMapping("/doAction")
    public Object doAction(@RequestParam(name = "infoId") String infoId,
                           @RequestParam(name = "userId") String userId,
                           @RequestParam(name = "status") String status,
                           @RequestParam(name = "type", defaultValue = "1") Integer type) {
        try {
            if (!"1".equals(status) && !"0".equals(status)) {
                return Result.ofFail("参数异常");
            }
            if (type != 0 && type != 1 && type != 2 && type != 3) {
                return Result.ofFail("参数异常");
            }
            infoId = "1";
            status = "1";
            userId = Integer.toString(integer.incrementAndGet());
            ActionDetailCO actionDetailCO = userActionService.doAction(userId, infoId, status, type);
            return Result.ofSuccess(actionDetailCO);
        } catch (DuplicateException e) {
            log.error("Duplicate action with userId {}, infoId {}, status {}", userId, infoId, status, e);
            return Result.ofFail(e.getMsg());
        } catch (ConcurrentException e) {
            log.error("Fail to get lock with userId {}, infoId {}, status {}", userId, infoId, status, e);
            return Result.ofFail(e.getMsg());
        } catch (ParamException e) {
            log.error("Fail to do action with wrong params userId {}, infoId {}, status {}", userId, infoId, status, e);
            return Result.ofFail(e.getMsg());
        } catch (Exception e) {
            log.error("Fail to do action userId {}, infoId {}, status {}", userId, infoId, status, e);
            return Result.ofFail();
        }
    }

    @GetMapping("/getActionDetail")
    public Object getStatus(@RequestParam(name = "userId") String userId,
                            @RequestParam(name = "infoId") String infoId) {
        try {
            String result = stringRedisTemplate.execute(getUserAction, Arrays.asList(userId, infoId));
            return Result.ofSuccess(JSON.parseObject(result, ActionDetailCO.class));
        } catch (Exception e) {
            log.error("Fail to get action detail with userId {}, infoId {}!", userId, infoId);
            return Result.ofFail();
        }
    }

    @GetMapping("/checkRes")
    public Object checkRes() {
        return userActionService.checkRes();
    }
}
