package com.dzl.myblog.controller;

import com.dzl.myblog.entity.Article;
import com.dzl.myblog.entity.Visitor;
import com.dzl.myblog.mapper.ArticleMapper;
import com.dzl.myblog.mapper.VisitorMapper;
import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.VisitorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;
    @Autowired
    VisitorService visitorService;
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
    @GetMapping("/gethotAtricle")
     public @ResponseBody
    JSONArray GetHotArtcle()
    {
        List<String> AtrcleIdList=new ArrayList<>();
        List<Visitor> hotAtrcle = visitorService.grtHotVistor();
        for (Visitor vi: hotAtrcle) {

            String AtrcleId=vi.getPageName().substring(8);
            AtrcleIdList.add(AtrcleId);
        }
        return articleService.getHorArticleByArticleId(AtrcleIdList);
    }
}
