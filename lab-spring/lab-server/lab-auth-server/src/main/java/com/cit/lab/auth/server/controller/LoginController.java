package com.cit.lab.auth.server.controller;

/**
 * @Author: Richard
 * @Description: LoginController
 * @CreateDate: 2025/5/19 21:48
 * @UpdateUser: zhouli
 * @UpdateDate: 2025/5/19 21:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginController {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Object login(String username, String password) {
        return null;
    }
    /**
     * 登出
     *
     * @return
     */
    public Object logout() {
        return null;
    }
    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public Object getCurrentUserInfo() {
        return null;
    }
    /**
     * 获取当前登录用户权限
     *
     * @return
     */
    public Object getCurrentUserPermission() {
        return null;
    }
    /**
     * 刷新token
     *
     * @return
     */
    public Object refreshToken() {
        return null;
    }
    /**
     * 交换第三方token
     *
     * @return
     */
    public Object exchangeThirdPartyToken() {
        return null;    }
}
