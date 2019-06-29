package com.dzl.myblog.service;

import com.dzl.myblog.entity.User;

/**
 * @author dzl
 * Describe:use业务操作
 */
public interface UserService {

    User SelectUserbyphone(String phone);

    User SelectUserandRolebyphone(String phone);

    int getNumberCount();

    /**
     * 通过用户名获得手机号
     * @param username 用户名
     * @return 手机号
     */
    String findPhoneByUsername(String username);
    /**
     * 通过手机号判断是否为超级用户
     * @param phone 手机号
     * @return true--超级管理员  false--非超级管理员
     */
    boolean isSuperAdmin(String phone);


}
