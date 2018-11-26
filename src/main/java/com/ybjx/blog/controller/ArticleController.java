package com.ybjx.blog.controller;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.result.ObjectResult;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ObjectResult<Boolean> addArticle(ArticleDTO article) {
        articleService.addArticle(article);
        return new ObjectResult<>(true);
    }

    @RequestMapping(value = "/content/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public ObjectResult<ArticleDTO> getArticle(@PathVariable("uuid") String uuid){
        ArticleDTO articleDTO = articleService.getArticleDto(uuid);

        if(articleDTO == null){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章没有找到");
        }

        return new ObjectResult<>(articleDTO);
    }
}
