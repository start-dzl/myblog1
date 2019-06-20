package com.dzl.myblog.service.impl;

import com.dzl.myblog.component.StringAndArray;
import com.dzl.myblog.entity.Article;
import com.dzl.myblog.mapper.ArticleMapper;
import com.dzl.myblog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ArticleServiceImpl implements ArticleService {

    private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public JSONArray findAllArticles(String rows, String pageNo) {
        int pageNum = Integer.parseInt(pageNo);
        int pageSize = Integer.parseInt(rows);

        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles = articleMapper.findAllArticles();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        List<Map<String, Object>> newArticles = new ArrayList<>();
        Map<String, Object> map;

        for(Article article : articles){
            map = new HashMap<>();
            map.put("thisArticleUrl", "/article/" + article.getArticleId());
            map.put("articleTags", StringAndArray.stringToArray(article.getArticleTags()));
            map.put("articleTitle", article.getArticleTitle());
            map.put("articleType", article.getArticleType());
            map.put("publishDate", article.getPublishDate());
            map.put("originalAuthor", article.getOriginalAuthor());
            map.put("articleCategories", article.getArticleCategories());
            map.put("articleTabloid", article.getArticleTabloid());
            map.put("likes", article.getLikes());
            newArticles.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(newArticles);
        Map<String, Object> thisPageInfo = new HashMap<>();
        thisPageInfo.put("pageNum",pageInfo.getPageNum());
        thisPageInfo.put("pageSize",pageInfo.getPageSize());
        thisPageInfo.put("total",pageInfo.getTotal());
        thisPageInfo.put("pages",pageInfo.getPages());
        thisPageInfo.put("isFirstPage",pageInfo.isIsFirstPage());
        thisPageInfo.put("isLastPage",pageInfo.isIsLastPage());

        jsonArray.add(thisPageInfo);
        return jsonArray;
    }

    @Override
    public Map<String, String> findArticleTitleByArticleId(long articleId) {
        Article articleInfo = articleMapper.findArticleTitleByArticleId(articleId);
        Map<String, String> articleMap = new HashMap<>();
        if(articleInfo != null){
            articleMap.put("articleTitle", articleInfo.getArticleTitle());
            articleMap.put("articleTabloid", articleInfo.getArticleTabloid());
        }
        return articleMap;
    }

    @Override
    public JSONObject getArticleByArticleId(long articleId, String username) {
        Article article = articleMapper.getArticleByArticleId(articleId);

        JSONObject jsonObject = new JSONObject();
        if(article != null){
            Article lastArticle = articleMapper.findArticleByArticleId(article.getLastArticleId());
            Article nextArticle = articleMapper.findArticleByArticleId(article.getNextArticleId());
            jsonObject.put("status","200");
            jsonObject.put("author",article.getAuthor());
            jsonObject.put("articleId",articleId);
            jsonObject.put("originalAuthor",article.getOriginalAuthor());
            jsonObject.put("articleTitle",article.getArticleTitle());
            jsonObject.put("publishDate",article.getPublishDate());
            jsonObject.put("updateDate",article.getUpdateDate());
            jsonObject.put("articleContent",article.getArticleContent());
            jsonObject.put("articleTags", StringAndArray.stringToArray(article.getArticleTags()));
            jsonObject.put("articleType",article.getArticleType());
            jsonObject.put("articleCategories",article.getArticleCategories());
            jsonObject.put("articleUrl",article.getArticleUrl());
            jsonObject.put("likes",article.getLikes());
            if(username == null){
                jsonObject.put("isLiked",0);
            }else {
               /* if(articleLikesRecordService.isLiked(articleId,username)){
                    jsonObject.put("isLiked",1);
                }else {
                    jsonObject.put("isLiked",0);
                }*/
            }
            if(lastArticle != null){
                jsonObject.put("lastStatus","200");
                jsonObject.put("lastArticleTitle",lastArticle.getArticleTitle());
                jsonObject.put("lastArticleUrl","/article/" + lastArticle.getArticleId());
            } else {
                jsonObject.put("lastStatus","500");
                jsonObject.put("lastInfo","无");
            }
            if(nextArticle != null){
                jsonObject.put("nextStatus","200");
                jsonObject.put("nextArticleTitle",nextArticle.getArticleTitle());
                jsonObject.put("nextArticleUrl","/article/" + nextArticle.getArticleId());
            } else {
                jsonObject.put("nextStatus","500");
                jsonObject.put("nextInfo","无");
            }
            return jsonObject;
        } else {
            jsonObject.put("status","500");
            jsonObject.put("errorInfo","获取文章信息失败");
            logger.error("获取文章id " + articleId + " 失败");
            return jsonObject;
        }
    }
}
