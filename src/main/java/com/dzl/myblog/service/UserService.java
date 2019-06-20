package com.dzl.myblog.service;

import com.dzl.myblog.entity.User;

/**
 * @author dzl
 * Describe:use业务操作
 */
public interface UserService {

    User SelectUserbyphone(String phone);

    User SelectUserandRolebyphone(String phone);

}
