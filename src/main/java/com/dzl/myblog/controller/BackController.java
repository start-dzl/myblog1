package com.dzl.myblog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class BackController {

    /**
     * 跳转首页
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response,
                        @AuthenticationPrincipal Principal principal){
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            request.getSession().removeAttribute("lastUrl");
            return "/index";
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
        return "index";
    }


}
