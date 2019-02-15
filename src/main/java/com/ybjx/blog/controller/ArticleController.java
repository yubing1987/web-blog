package com.ybjx.blog.controller;

import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章相关的接口
 * Created by YuBing on 2018/11/19.
 */
@RequestMapping("/article/")
@RestController
public class ArticleController {

    /**
     * 文章相关的服务
     */
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 添加文章
     * @param article 文章信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<Boolean> addArticle(ArticleDTO article) {
        articleService.addArticle(article);
        return new PojoResult<>(true);
    }

    /**
     * 通过文章ID获取文章信息
     * @param id 文章ID
     * @return 文章信息
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PojoResult<ArticleDTO> getArticle(@PathVariable("id") Integer id){
        ArticleDO articleDO = articleService.getArticleById(id);

        if(articleDO == null){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章没有找到");
        }
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(articleDO, articleDTO);
        return new PojoResult<>(articleDTO);
    }
}
