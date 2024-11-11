package com.cit.lab.action.server.exception;

/**
 * @Author: Richard
 * @Description: ParamException
 * @CreateDate: 2023/4/6 00:17
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/6 00:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ParamException extends RuntimeException {
    private final String msg;

    public ParamException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}

