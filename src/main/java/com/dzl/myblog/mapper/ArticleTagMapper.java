package com.dzl.myblog.mapper;

import com.dzl.myblog.entity.ArticleTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface ArticleTagMapper {


    @Insert("insert into article_tag(tagName,tagSize) values(#{tagName},#{tagSize})")
    void insertTag(ArticleTag tag);

    @Select("select IFNULL(max(id),0) from article_tag where tagName COLLATE utf8_bin=#{tagName}")
    int findIsExitByTagName(@Param("tagName") String tagName);

    @Select("select * from article_tag order by id desc")
    List<ArticleTag> findTagsCloud();

    @Select("select count(*) from article_tag")
    int countTagsNum();

    @Select("select tagSize from article_tag where tagName=#{tagName}")
    int getTagsSizeByTagName(String tagName);
}
