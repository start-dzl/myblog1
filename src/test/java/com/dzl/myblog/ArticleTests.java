package com.dzl.myblog;

import com.dzl.myblog.service.ArticleService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTests {

    @Autowired
    ArticleService articleService;

    @Test
    public void contextLoads() {
        JSONArray allArticles = articleService.findAllArticles("3", "0");
        System.out.print(allArticles);
    }

}
