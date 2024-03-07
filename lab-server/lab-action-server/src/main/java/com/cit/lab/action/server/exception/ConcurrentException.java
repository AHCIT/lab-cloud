package com.cit.lab.action.server.exception;

/**
 * @Author: Richard
 * @Description: 分布式锁获取异常
 * @CreateDate: 2023/4/5 20:06
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 20:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ConcurrentException extends RuntimeException {
    private final String msg;

    public ConcurrentException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
