package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.ObjectResult;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章相关的接口
 * Created by YuBing on 2018/11/19.
 */
@RequestMapping("/article/")
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ObjectResult<Boolean> getArticlePage(ArticleDTO article) {
        articleService.addArticle(article);
        return new ObjectResult<>(true);
    }
}
