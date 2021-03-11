package com.dzl.myblog.service.impl;

import com.dzl.myblog.mapper.ArticleTagMapper;
import com.dzl.myblog.service.ArticeTagService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticeTagServiceImpl implements ArticeTagService {

    @Autowired
    ArticleTagMapper articleTagMapper;

/*
    @Override
    public void addTags(String[] tags, int tagSize) {
        for(String tag : tags){
            if(articleTagMapper.findIsExitByTagName(tag) == 0){
                ArticleTag t = new ArticleTag(tag, tagSize);
                articleTagMapper.insertTag(t);
            }
        }
    }

    @Override
    public JSONObject findTagsCloud() {
        List<ArticleTag> tags = articleTagMapper.findTagsCloud();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",200);
        jsonObject.put("result", JSONArray.fromObject(tags));
        jsonObject.put("tagsNum",tags.size());
        return jsonObject;
    }

    @Override
    public int countTagsNum() {
        return articleTagMapper.countTagsNum();
    }

    @Override
    public int getTagsSizeByTagName(String tagName) {
        return articleTagMapper.getTagsSizeByTagName(tagName);
    }*/
}
