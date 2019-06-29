package com.dzl.myblog.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ArticleCategoryService {


    /**
     * 获得所有的分类以及该分类的文章总数
     * @return
     */
    JSONObject findCategoriesNameAndArticleNum();

    /**
     * 获得所有的分类
     * @return
     */
    JSONArray findCategoriesName();

    /**
     * 获得分类数目
     * @return
     */
    int countCategoriesNum();
}
