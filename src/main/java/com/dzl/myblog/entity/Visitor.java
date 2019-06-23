package com.dzl.myblog.entity;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(long visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

}
