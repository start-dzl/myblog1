package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.UserService;
import com.dzl.myblog.service.VisitorService;
import com.dzl.myblog.utils.TransCodingUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Slf4j
@Controller
public class BackController {

    @Autowired
    VisitorService visitorService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

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
           return "index";

        }
        Cookie[] cookies = request.getCookies();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));;
        return "index";
    }

    @GetMapping("/adminindex")
    public String adminIndex(HttpServletRequest request, HttpServletResponse response){
        return "adminindex";
    }

    /**
     * 获得统计信息
     * @return
     */
    @GetMapping("/getStatisticsInfo")
    @ResponseBody
    public JSONObject getStatisticsInfo(){
        JSONObject returnJson = new JSONObject();
        long num = visitorService.getAllVisitor();
        returnJson.put("allVisitor", num);
        returnJson.put("allUser", userService.getNumberCount());
       // returnJson.put("yesterdayVisitor", num);
        returnJson.put("articleNum",articleService.getArticleCount() );
        return returnJson;
    }
    /**
     * 跳转标签页
     * @return
     */
    @GetMapping("/tags")
    public String getTags(HttpServletResponse response,
                          HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        String tag = request.getParameter("tag");
        try {
            response.setHeader("tag", TransCodingUtil.stringToUnicode(tag));
        } catch (Exception e){
        }
        return "Tags";
    }

    /**
     * 跳转标签页
     * @return
     */
    @GetMapping("/category")
    public String getCategory(HttpServletResponse response,
                          HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        String Category = request.getParameter("category");
        try {
            response.setHeader("category", TransCodingUtil.stringToUnicode(Category));
        } catch (Exception e){
        }
        return "Category";
    }



}
