package com.dzl.myblog.controller;

import com.dzl.myblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/login")
    String login()
    {
        return "login";
    }
}
