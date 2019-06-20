package com.dzl.myblog.service;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Map;

public interface ArticleService {

     /**
      * 分页获得所有文章
      * @param rows 一页显示文章数
      * @param pageNo 第几页
      * @return 该页所有文章
      */
     JSONArray findAllArticles(String rows, String pageNo);

     /**
      * 分页获得所有文章
      * @param articleId 文章id
      * @return 文章名
      */
     Map<String, String> findArticleTitleByArticleId( long articleId);

     /**
      * 获得文章
      * @param articleId 文章id
      * @return
      */
     JSONObject getArticleByArticleId(long articleId, String username);
}
