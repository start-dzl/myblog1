package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.VisitorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    ArticleService articleService;

    @Autowired
    VisitorService visitorService;

   /* *//**
     * 分页获得当前页文章
     * @param rows 一页的大小
     * @param pageNum 当前页
     *//*
    @PostMapping("/myArticles")
    public @ResponseBody
    JSONArray myArticles(@RequestParam("rows") String rows,
                         @RequestParam("pageNum") String pageNum){

        return articleService.findAllArticles(rows, pageNum);

    }

    *//**
     * 跳转到文章显示页
     *//*
    @GetMapping("/article/{articleId}")
    public String show(@PathVariable("articleId") long articleId,
                       HttpServletResponse response,
                       Model model,
                       HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        Map<String, String> articleMap = articleService.findArticleTitleByArticleId(articleId);
        if(articleMap.get("articleTitle") != null){
            model.addAttribute("articleTitle",articleMap.get("articleTitle"));
            String articleTabloid = articleMap.get("articleTabloid");
            if(articleTabloid.length() <= 110){
                model.addAttribute("articleTabloid",articleTabloid);
            } else {
                model.addAttribute("articleTabloid",articleTabloid.substring(0,110));
            }
        }
        //将文章id存入响应头
        response.setHeader("articleId",String.valueOf(articleId));
        return "show";
    }

    *//**
     * 增加访客量
     * @return  网站总访问量以及访客量
     *//*
    @GetMapping("/getVisitorNumByPageName")
    public @ResponseBody
    JSONObject getVisitorNumByPageName(HttpServletRequest request,
                                       @RequestParam("pageName") String pageName) throws UnsupportedEncodingException {

        int index = pageName.indexOf("/");
        if(index == -1){
            pageName = "visitorWithoutArticle";
        } else {
            String subPageName = pageName.substring(0, index);
            if("archives".equals(subPageName) || "categories".equals(subPageName) || "tags".equals(subPageName) || "login".equals(subPageName) || "register".equals(subPageName)){
                pageName = "visitorWithoutArticle";
            }
        }
        visitorService.addVisitorNumByPageName(pageName, request);
        return visitorService.getVisitorNumByPageName(pageName);
    }*/


}
