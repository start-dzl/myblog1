package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleService;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminIndexControl {

    @Autowired
    ArticleService articleService;

    @GetMapping("/getArticleManagement")
    @ResponseBody
    public JSONObject getArticleManagement(@AuthenticationPrincipal Principal principal,
                                            @RequestParam("rows") String rows,@RequestParam("pageNum") String pageNum)
    {
        String userName=null;
        JSONObject returnJson=new JSONObject();
        try{
            userName=principal.getName();
        }catch (NullPointerException ex)
        {
            returnJson.put("status",403);
            return  returnJson;
        }
        return articleService.getArticleManagement(Integer.parseInt(rows), Integer.parseInt(pageNum));
    }
    @GetMapping("/deleteArticle")
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @ResponseBody
    public int deleteArticle(@RequestParam("id") int id)
    {
        return articleService.deleteArticleById(id);
    }

}
