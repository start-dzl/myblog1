package com.dzl.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
@Mapper
@Repository
public interface ArticleCategoryMapper {

    @Select("select categoryName from article_category")
    List<String> findCategoriesName();

    @Select("select count(*) from article_category")
    int countCategoriesNum();

}
