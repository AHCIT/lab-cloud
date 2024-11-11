package com.cit.lab.action.server.exception;

/**
 * @Author: Richard
 * @Description: 重复操作异常
 * @CreateDate: 2023/4/5 19:41
 * @UpdateUser: Richard
 * @UpdateDate: 2023/4/5 19:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DuplicateException extends RuntimeException {
    private final String msg;

    public DuplicateException(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }
}
