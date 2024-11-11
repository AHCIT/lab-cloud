package com.cit.lab.api.action.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: UserActionAPI
 * @Author: Richard
 * @CreateDate: 2024/1/20 22:33
 * @UpdateAuthor: Richard
 * @UpdateDate: 2024/1/20 22:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface UserActionAPI {
    Object doAction(@RequestParam(name = "infoId") String infoId,
                    @RequestParam(name = "userId") String userId,
                    @RequestParam(name = "status") String status,
                    @RequestParam(name = "sleep", defaultValue = "0") Long sleep,
                    @RequestParam(name = "type", defaultValue = "1") Integer type);

    @GetMapping("/getActionDetail")
    Object getStatus(@RequestParam(name = "userId") String userId,
                     @RequestParam(name = "infoId") String infoId);
}
