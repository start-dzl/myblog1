package com.dzl.myblog.entity;

import lombok.Data;

@Data
public class ArticleTag {

    private int id;

    /**
     * 标签名
     */
    private String tagName;



    /**
     * 标签大小
     */
    private int tagSize;

    public ArticleTag() {
    }

    public ArticleTag(String tagName, int tagSize) {
        this.tagName = tagName;
        this.tagSize = tagSize;
    }
}
