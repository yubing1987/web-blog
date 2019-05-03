package com.ybjx.blog.controller;

import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.query.ArticleQuery;
import com.ybjx.blog.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章相关的接口
 * @author ybjx
 * @date 2019/5/3 9:43
 */
@RestController
@RequestMapping(value = "/api")
public class ArticleController {

    /**
     * 文章相关的服务
     */
    private final ArticleService articleService;

    /**
     * 构造方法
     * @param articleService -
     */
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 添加文章
     * @param articleDTO 文章信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/manage/article", method = RequestMethod.POST)
    public PojoResult<Boolean> addArticle(ArticleDTO articleDTO){
        articleService.addArticle(articleDTO);
        return new PojoResult<>(true);
    }

    /**
     * 删除文章
     * @param articleId 文章ID
     * @return 是否删除成功
     */
    @RequestMapping(value = "/manage/article/{articleId}/del", method = RequestMethod.POST)
    public PojoResult<Boolean> delArticle(@PathVariable("articleId") Integer articleId){
        articleService.delArticle(articleId);
        return new PojoResult<>(true);
    }

    /**
     * 编辑文章
     * @param articleDTO 文章信息
     * @return 是否编辑成功
     */
    @RequestMapping(value = "/manage/article/edit", method = RequestMethod.POST)
    public PojoResult<Boolean> editArticle(ArticleDTO articleDTO){
        articleService.editArticle(articleDTO);
        return new PojoResult<>(true);
    }

    /**
     * 查找属于登录者的文章列表
     * @param query 查询信息
     * @return 查找到的文章列表
     */
    @RequestMapping(value = "/manage/article/list", method = RequestMethod.GET)
    public PageResult<ArticleDTO> getOwnerArticleList(ArticleQuery query){
        query.setUserId(UserHolder.getUser().getId());
        return articleService.getArticleList(query);
    }

    /*
    * *************************************************************************************************************
    * 以下接口为无需校验权限的接口
    * *************************************************************************************************************
    */
    /**
     * 通过文章ID查询文章信息
     * @param articleId 文章ID
     * @return 文章信息
     */
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public PojoResult<ArticleDTO> getArticleById(@PathVariable("articleId") Integer articleId){
        ArticleDO articleDO = articleService.getArticleById(articleId);
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(articleDO, articleDTO);
        // todo 需要再查找文章的标签然后添加到DTO中去
        return new PojoResult<>(articleDTO);
    }


    /**
     * 查找文章列表
     * @param query 查询信息
     * @return 查找到的文章列表
     */
    @RequestMapping(value = "/article/list", method = RequestMethod.GET)
    public PageResult<ArticleDTO> getArticleList(ArticleQuery query){
        return articleService.getArticleList(query);
    }
}
