package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    /**
     *  获取文章
     * @param articleId 文章id
     * @return
     */
    @PostMapping("/getArticleByArticleId")
    public @ResponseBody
    JSONObject getArticleById(@RequestParam("articleId") String articleId,
                              @AuthenticationPrincipal Principal principal){
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e){
            logger.info("This user is not login");
        }
        JSONObject jsonObject = articleService.getArticleByArticleId(Long.parseLong(articleId),username);
        return jsonObject;
    }
}
