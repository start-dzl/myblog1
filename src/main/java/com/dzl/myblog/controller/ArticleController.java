package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.VisitorService;
import com.dzl.myblog.utils.TransCodingUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;
    @Autowired
    VisitorService visitorService;

   /* *//**
     * 获取文章
     *
     * @param articleId 文章id
     * @return
     *//*
    @PostMapping("/getArticleByArticleId")
    public @ResponseBody
    JSONObject getArticleById(@RequestParam("articleId") String articleId,
                              @AuthenticationPrincipal Principal principal) {
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            logger.info("This user is not login");
        }
        JSONObject jsonObject = articleService.getArticleByArticleId(Long.parseLong(articleId), username);
        return jsonObject;
    }

    @GetMapping("/gethotAtricle")
    public @ResponseBody
    JSONArray GetHotArtcle() {
        List<String> AtrcleIdList = new ArrayList<>();
        List<Visitor> hotAtrcle = visitorService.grtHotVistor();
        for (Visitor vi : hotAtrcle) {

            String AtrcleId = vi.getPageName().substring(8);
            AtrcleIdList.add(AtrcleId);
        }
        return articleService.getHorArticleByArticleId(AtrcleIdList);
    }

    @PostMapping("/getTagArticle")
    @ResponseBody
    public JSONObject getTagArticle(@RequestParam("tag") String tag, HttpServletRequest request) {
        try {
            tag = TransCodingUtil.unicodeToString(tag);
        } catch (Exception e) {
        }
        if ("".equals(tag)) {
            return null;
        } else {
            int rows = Integer.parseInt(request.getParameter("rows"));
            int pageNum = Integer.parseInt(request.getParameter("pageNum"));
            return articleService.findArticlebyTag(tag, rows, pageNum);
        }
    }*/
}
