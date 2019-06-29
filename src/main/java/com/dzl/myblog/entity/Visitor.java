package com.dzl.myblog.entity;

import lombok.Data;

@Data
public class Visitor {

    private int id;
    /**
     * 访客人数
     */
    private long visitorNum;
    /**
     * 当前页的name or 文章名
     */
    private String pageName;

}
