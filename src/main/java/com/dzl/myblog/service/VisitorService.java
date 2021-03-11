package com.dzl.myblog.service;

import com.dzl.myblog.entity.Visitor;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VisitorService {

    /**
     * 通过页名增加访客量
     * @param pageName
     *//*
    void addVisitorNumByPageName(String pageName, HttpServletRequest request);

    *//**
     * 通过页名获得总访问量和访客量
     * @param pageName 页名
     * @return
     *//*
    JSONObject getVisitorNumByPageName(String pageName);

    *//**
     * 通过页名获得访客量
     * @param pageName 页名
     * @return 访客量
     *//*
    long getNumByPageName(String pageName);

    *//**
     * 发布文章后保存该文章的访客量
     * @param pageName 文章url
     *//*
    void insertVisitorArticlePage(String pageName);

    *//**
     * 获得总访问量
     * @return
     *//*
    long getAllVisitor();

    *//**
     * 当文章删除的时候，浏览记录也跟着删除
     * @param VisitorPage
     *//*
    void deleteVisitorArticlePage(String VisitorPage);*/

    /**
     * 获得了热门的文章页
     * @return
     */
    List<Visitor> grtHotVistor();
}
