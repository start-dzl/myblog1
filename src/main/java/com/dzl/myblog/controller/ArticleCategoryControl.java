package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleCategoryService;
import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.utils.TransCodingUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import com.dzl.myblog.utils.TransCodingUtil;

@Controller
@ResponseBody
public class ArticleCategoryControl {
    @Autowired
    ArticleCategoryService articleCategoryService;
    @Autowired
    ArticleService articleService;

    /**
     * 获得所有的分类
     * @return
     */
    @GetMapping("/findCategoriesName")
    public List<String> findCategoriesName(){
        return articleCategoryService.findCategoriesName();
    }


    /**
     * 获得所有分类名以及每个分类名的文章数目
     * @return
     */
    @GetMapping("/findCategoriesNameAndArticleNum")
    public JSONObject findCategoriesNameAndArticleNum(){
        return articleCategoryService.findCategoriesNameAndArticleNum();
    }

    /**
     * 分页获得该分类下的文章
     * @return
     */
    @GetMapping("/getCategoryArticle")
    public JSONObject getCategoryArticle(@RequestParam("category") String category,
                                         HttpServletRequest request){

        try {
            category = TransCodingUtil.unicodeToString(category);
        } catch (Exception e){
        }
        int rows = Integer.parseInt(request.getParameter("rows"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        return articleService.findArticleByCategory(category, rows, pageNum);
    }
}
