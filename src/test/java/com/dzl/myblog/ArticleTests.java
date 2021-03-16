package com.dzl.myblog;

import com.dzl.myblog.entity.Visitor;
import com.dzl.myblog.mapper.ArticleMapper;
import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.VisitorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseCookie;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ArticleTests {

    @Autowired
    ArticleService articleService;
    @Autowired
    VisitorService visitorService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TestRestTemplate restTemplate;

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

    @Test
    public void getArticleById() {
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("username", "15200343995");
        map.add("password", "123456");
        EntityExchangeResult<Void> result = webTestClient.post().uri("/login")
                .syncBody(map)
                .exchange()
                .expectBody().isEmpty();
        ResponseCookie session = result.getResponseCookies().getFirst("JSESSIONID");

        webTestClient
                .post().uri("/getArticleByArticleId?articleId=1")
                .cookie(session.getName(), session.getValue())
                .exchange()
                .expectStatus().isOk();

    }

}
