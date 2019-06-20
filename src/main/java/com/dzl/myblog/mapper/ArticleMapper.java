package com.dzl.myblog.mapper;

import com.dzl.myblog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

}
