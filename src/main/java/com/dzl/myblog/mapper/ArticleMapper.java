package com.dzl.myblog.mapper;

import com.dzl.myblog.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    @Select("select articleId,articleTitle from article where articleId=#{articleId}")
    Article findArticleByArticleId(@Param("articleId") long articleId);

    @Select("select articleId,originalAuthor,articleTags,articleTitle,articleType,publishDate,originalAuthor,articleCategories,articleTabloid,likes from article order by id desc")
    List<Article> findAllArticles();

    @Select("select articleTitle,articleTabloid from article where articleId=#{articleId}")
    Article findArticleTitleByArticleId(@Param("articleId") long articleId);

    @Select("select * from article where articleId=#{articleId}")
    Article getArticleByArticleId(@Param("articleId") long articleId);

    @Select("select id,articleId,originalAuthor,articleTitle,articleCategories,publishDate from article order by id desc")
    List<Article> getArticleManagement();

    @Delete("delete from article where id=#{id}")
    int deleteArticleById(@Param("id") int id);

    @Select("select articleId from article where id=#{id}")
    Article findAllArticleId(long id);
    @Select("select count(*) from article")
    int getArticleCount();

    @Select("select count(*) from article where articleCategories=#{category}")
    int countArticleCategoryByCategory(@Param("category") String category);

    @Select("select articleId,originalAuthor,articleTitle,articleTags,articleType,articleCategories,publishDate from article order by id desc")
    List<Article> findAllArticlesPartInfo();

    @Select("select articleId,originalAuthor,articleTitle,articleTags,articleType,articleCategories,publishDate from article where articleCategories=#{category} order by id desc")
    List<Article> findArticleByCategory(@Param("category") String category);

    @Select("select id,articleId,originalAuthor,articleTitle,articleContent,articleCategories,articleTags,articleType,articleUrl from article where id=#{id}")
    Article findArticleById(int id);

    @Select("select articleId from article where id=#{id}")
    Article getArticleUrlById(int id);

    @Update("update article set originalAuthor=#{originalAuthor},articleTitle=#{articleTitle},updateDate=#{updateDate},articleContent=#{articleContent},articleTags=#{articleTags},articleType=#{articleType},articleCategories=#{articleCategories},articleUrl=#{articleUrl},articleTabloid=#{articleTabloid} where id=#{id}")
    void updateArticleById(Article article);

    @Select("select articleId from article order by id desc limit 1")
    Article findEndArticleId();

    @Insert("insert into article(articleId,author,originalAuthor,articleTitle,publishDate,updateDate,,articleTags,articleType,articleCategories,articleUrl,articleTabloid,likes,lastArticleId,nextArticleId) " +
            "values(#{articleId},#{author},#{originalAuthor},#{articleTitle},#{publishDate},#{updateDate},#{articleContent},#{articleTags},#{articleType},#{articleCategories},#{articleUrl},#{articleTabloid},#{likes},#{lastArticleId},#{nextArticleId})")
    void insertArticle(Article article);

    @Select("update article set lastArticleId=#{lastArticleId} where articleId=#{articleId}")
    void updateArticleLastId(@Param("lastArticleLd") long lastArticleLd, @Param("articleId") long articleId);

    @Select("update article set nextArticleId=#{nextArticleId} where articleId=#{articleId}")
    void updateArticleNextId(@Param("nextArticleId") long nextArticleId, @Param("articleId") long articleId);

    @Select("<script>select articleTitle,articleId from article where articleId in  " +
            "<foreach item='item' index='index' Ecollection='articleIdList' open='(' separator=',' close=')'> #{item} </foreach></script>")
    List<Article> finhotArticlebyId(@Param("articleIdList") List<String> articleIdList);

    @Select("select articleId,articleTitle,articleTags,publishDate from article where articleTags like '%${tag}%' order by id desc")
    List<Article> findArticlebyTag(@Param("tag") String tag);

}
