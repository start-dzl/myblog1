package com.dzl.myblog.service.impl;

import com.dzl.myblog.mapper.UserMapper;
import com.dzl.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

   /* public UserServiceImpl() {
        super();
    }

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

    @Override
    public int getNumberCount() {
        return userMapper.UseNumberCount();
    }

    @Override
    public String findPhoneByUsername(String username) {
            return userMapper.findPhoneByUsername(username);
    }

    @Override
    public boolean isSuperAdmin(String phone) {
        int userId = userMapper.findUserIdByPhone(phone);
        List<Object> roleIds = userMapper.findRoleIdByUserId(userId);

        for(Object i : roleIds){
            if((int)i == 3){
                return true;
            }
        }
        return false;
    }*/
}
