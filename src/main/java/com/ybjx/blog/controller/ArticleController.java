package com.ybjx.blog.controller;

import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.PagingCheck;
import com.ybjx.blog.checker.group.QueryCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.query.ArticleQuery;
import com.ybjx.blog.service.ArticleService;
import com.ybjx.blog.service.ArticleTagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章相关的接口
 * Created by YuBing on 2018/11/19.
 */
@RequestMapping("/api/article/")
@RestController
public class ArticleController {

    /**
     * 文章相关的服务
     */
    private final ArticleService articleService;

    /**
     * 标签服务
     */
    private final ArticleTagService tagService;

    @Autowired
    public ArticleController(ArticleService articleService,
                             ArticleTagService tagService) {
        this.articleService = articleService;
        this.tagService = tagService;
    }

    /**
     * 添加文章
     * @param article 文章信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<ArticleDTO> addArticle(@RequestBody ArticleDTO article) {
        ArticleDO articleDO = articleService.addArticle(article);
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(articleDO, articleDTO);
        return new PojoResult<>(articleDTO);
    }

    /**
     * 通过文章ID获取文章信息
     * @param id 文章ID
     * @return 文章信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PojoResult<ArticleDTO> getArticle(@PathVariable("id") Integer id) {
        ArticleDO articleDO = articleService.getArticleById(id);

        if (articleDO == null) {
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章没有找到");
        }
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(articleDO, articleDTO);
        articleDTO.setTags(tagService.getArticleTagList(id));
        return new PojoResult<>(articleDTO);
    }

    /**
     * 修改文章发布状态
     * @param id 文章ID
     * @param published 文章发布状态，true--已发布，false--未发布
     * @return 是否修改成功
     */
    @RequestMapping(value = "/{id}/published/{published}", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<Boolean> articlePublished(@PathVariable("id") Integer id,
                                                @PathVariable("published") Boolean published) {
        articleService.articlePublished(id, published);
        return new PojoResult<>(true);
    }

    /**
     * 修改文章信息
     * @param articleDTO 文章信息
     * @return 是否修改成功
     */
    @RequestMapping(value = "/edited", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<Boolean> articleEdited(@RequestBody ArticleDTO articleDTO) {
        articleService.editArticle(articleDTO);
        return new PojoResult<>(true);
    }

    /**
     * 删除文章
     * @param id 文章ID
     * @return 是否删除成功
     */
    @RequestMapping(value = "/{id}/deleted", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<Boolean> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return new PojoResult<>(true);
    }

    /**
     * 查询文章列表
     * @param query 查询条件
     * @return 查询到的文章列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    @ParameterCheck({QueryCheck.class, PagingCheck.class})
    public PageResult<ArticleDTO> queryArticle(ArticleQuery query) {
        PageResult<ArticleDTO> result = articleService.queryArticle(query.getPage(),
                query.getSize(), query.getKey(), query.getTag(),false);
        for(ArticleDTO article: result.getContent().getItems()){
            article.setTags(tagService.getArticleTagList(article.getId()));
        }
        return result;
    }

    /**
     * 查询已经发布的文章
     * @param query 查询条件
     * @return 文章列表
     */
    @RequestMapping(value = "/list/published", method = RequestMethod.GET)
    @ResponseBody
    @ParameterCheck({QueryCheck.class, PagingCheck.class})
    public PageResult<ArticleDTO> queryPublishArticle(ArticleQuery query){
        return articleService.queryArticle(query.getPage(), query.getSize(), query.getKey(), query.getTag(), true);
    }
}
