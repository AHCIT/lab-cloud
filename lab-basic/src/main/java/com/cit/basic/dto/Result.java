package com.cit.basic.dto;


import com.cit.basic.enums.ReturnCode;
import lombok.Data;

/**
 * @Description: 基础返回对象
 * @Author: Richard
 * @CreateDate: 2023/9/11 23:02
 * @UpdateUser: Richard
 * @UpdateDate: 2023/9/11 23:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Data
public class Result<T> {


    /**
     * 标识 大于0成功，<0 失败
     */
    private int flag;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;

    public Result() {
        this.flag = ReturnCode.SUCCESS.getCode();
        this.msg = "ok";
    }

    public Result(T data) {
        this.flag = ReturnCode.SUCCESS.getCode();
        this.msg = "ok";
        this.data = data;
    }

    public Result(int flag) {
        this.flag = flag;
        this.msg = "ok";
    }

    public Result(int flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Result(int flag, String msg, T data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ofSuccess() {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg("ok");
        return brd;
    }

    public static <T> Result<T> ofSuccess(T data) {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg("ok");
        brd.setData(data);
        return brd;
    }

    public static <T> Result<T> ofSuccess(T data, String message) {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg(message);
        brd.setData(data);
        return brd;
    }

    public static <T> Result<T> ofSuccessMsg(String msg) {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.SUCCESS.getCode());
        brd.setMsg(msg);
        return brd;
    }

    public static <T> Result<T> ofFail() {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.FAIL.getCode());
        brd.setMsg("fail");
        return brd;
    }

    public static <T> Result<T> ofFail(String msg) {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.FAIL.getCode());
        brd.setMsg(msg);
        return brd;
    }

    public static <T> Result<T> ofFail(Integer flag, String msg) {
        Result<T> brd = new Result<>();
        brd.setFlag(flag);
        brd.setMsg(msg);
        return brd;
    }

    public static <T> Result<T> ofThrowable(int flag, Throwable throwable) {
        Result<T> brd = new Result<>();
        brd.setFlag(flag);
        brd.setMsg(throwable.getClass().getName() + ", " + throwable.getMessage());
        return brd;
    }

    public static <T> Result<T> ofThrowable(Throwable throwable) {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.FAIL.getCode());
        brd.setMsg(throwable.getMessage());
        return brd;
    }

    public static <T> Result<T> ofException(T data) {
        Result<T> brd = new Result<>();
        brd.setFlag(ReturnCode.PARAMETER.getCode());
        brd.setMsg("ok");
        brd.setData(data);
        return brd;
    }

}
