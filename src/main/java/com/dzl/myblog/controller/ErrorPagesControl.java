package com.dzl.myblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPagesControl {


    @RequestMapping("/error404Page")
    public String error404(){
        return "error404Page";
    }

    @RequestMapping("/error401Page")
    public String error401(){
        return "error401Page";
    }

    @RequestMapping("/error403Page")
    public String error403(){
        return "error403Page";
    }

    @RequestMapping("/error500Page")
    public String error500(){
        return "error500Page";
    }





}
