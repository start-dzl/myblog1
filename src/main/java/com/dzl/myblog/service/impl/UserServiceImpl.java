package com.dzl.myblog.service.impl;

import com.dzl.myblog.entity.User;

import com.dzl.myblog.mapper.UserMapper;
import com.dzl.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User SelectUserbyphone(String phone) {

       User user= userMapper.SelectUserByPhone(phone);
        return user;
    }

    @Override
    public User SelectUserandRolebyphone(String phone) {
        User user= userMapper.getUsernameAndRolesByPhone(phone);
        return user;
    }
}
