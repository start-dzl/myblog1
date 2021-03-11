package com.dzl.myblog;

import com.dzl.myblog.entity.Article;
import com.dzl.myblog.entity.ArticleExample;
import com.dzl.myblog.entity.Visitor;
import com.dzl.myblog.mapper.ArticleMapper;
import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.VisitorService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTests {

    @Autowired
    ArticleService articleService;
    @Autowired
    VisitorService visitorService;

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void contextLoads() {
        List<Visitor> visitors = visitorService.grtHotVistor();
        for (Visitor visitor : visitors) {
            System.out.println(visitor.getVisitorNum());
            System.out.println(visitor.getPageName());
        }

    }

}
