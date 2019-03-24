package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.ListResult;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.query.RelatedArticleQuery;
import com.ybjx.blog.service.ArticleRelatedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create by YuBing at 2019/2/20
 */
@RestController
@RequestMapping("/api/relation")
public class ArticleRelatedController {
    /**
     * 关联文章相关的服务
     */
    private final ArticleRelatedService relatedService;

    public ArticleRelatedController(ArticleRelatedService relatedService) {
        this.relatedService = relatedService;
    }

    /**
     * 创建关联文章接口
     * @param id1 文章ID1
     * @param id2 文章ID2
     * @return 是否操作成功
     */
    @RequestMapping(value = "/create/{id1}/{id2}", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<Boolean> createRelation(@PathVariable("id1") Integer id1,
                                             @PathVariable("id2") Integer id2) {
        relatedService.createRelated(id1, id2);
        return new PojoResult<>(true);
    }

    /**
     * 删除关联文章接口
     * @param id1 文章ID1
     * @param id2 文章ID2
     * @return 是否操作成功
     */
    @RequestMapping(value = "/deleted/{id1}/{id2}", method = RequestMethod.POST)
    @ResponseBody
    public PojoResult<Boolean> deletedRelation(@PathVariable("id1") Integer id1,
                                             @PathVariable("id2") Integer id2) {
        relatedService.deletedRelation(id1, id2);
        return new PojoResult<>(true);
    }

    /**
     * 通过文章ID查询关联文章列表
     * @param id 文章ID
     * @return 文章列表
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ListResult<ArticleDTO> getRelatedArticle(@PathVariable("id") Integer id) {
        List<ArticleDTO> list = relatedService.getRelatedArticle(id);
        return new ListResult<>(list);
    }

    /**
     * 通过文章ID查询未关联的文章列表
     * @param id 文章ID
     * @param query 查询信息
     * @return 文章列表
     */
    @RequestMapping(value = "/unrelated/{id}")
    @ResponseBody
    public ListResult<ArticleDTO> getUnRelatedArticle(@PathVariable("id") Integer id,
                                                      RelatedArticleQuery query){
        List<ArticleDTO> list = relatedService.getUnrelatedArticle(id, query.getKey());
        return new ListResult<>(list);
    }
}
