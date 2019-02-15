package com.ybjx.blog.controller;

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
    public PojoResult<Boolean> addArticle(@RequestBody ArticleDTO article) {
        articleService.addArticle(article);
        return new PojoResult<>(true);
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
}
