package com.dzl.myblog.service;

import com.dzl.myblog.entity.Article;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ArticleService {

    /**
     * 分页获得所有文章
     *
     * @param rows   一页显示文章数
     * @param pageNo 第几页
     * @return 该页所有文章
     */
    JSONArray findAllArticles(String rows, String pageNo);

    /**
     * 分页获得所有文章
     *
     * @param articleId 文章id
     * @return 文章名
     */
    Map<String, String> findArticleTitleByArticleId(long articleId);

    /**
     * 获得文章
     *
     * @param articleId 文章id
     * @return
     */
    JSONObject getArticleByArticleId(long articleId, String username);

    JSONObject getArticleManagement(int rows, int pageNum);

    /**
     * 删除文章
     *
     * @param Id
     * @return
     */
    int deleteArticleById(int Id);
    /*
    获得所有的文章数
     */
    int getArticleCount();

    /**
     * 计算该分类文章的数目
     * @param category 分类名
     * @return 该分类下文章的数目
     */
    int countArticleCategoryByCategory(String category);

    /**
     * 分页获得该分类下的所有文章
     * @param category 分类名
     * @param rows 一页大小
     * @param pageNum 页数
     * @return
     */
    JSONObject findArticleByCategory(String category, int rows, int pageNum);

    /**
     * 通过id获取文章
     */
    Article findArticleById(int id);

    /**
     * 获得草稿中的文章
     * @return
     */
    JSONObject getDraftArticle(Article article, String[] articleTags, int articleGrade);

    /**
     * 修改文章
     * @return
     */
    @Transactional
    JSONObject updateArticleById(Article article);

    /**
     * 保存文章
     * @param article 文章
     * @return  status: 200--成功   500--失败
     */

    JSONObject insertArticle(Article article);

    /**
     * 通过文章id更新它的上一篇或下一篇文章id
     * @param lastOrNext
     * @param lastOrNextArticleId
     * @param articleId
     */
    void updateArticleLastOrNextId(String lastOrNext, long lastOrNextArticleId, long articleId);

    /**
     * 获得热门文章
     * @param ArticleList
     * @return
     */
    JSONArray getHorArticleByArticleId(List<String> ArticleList);




}
