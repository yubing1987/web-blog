package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleTagDTO;
import com.ybjx.blog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章标签相关的接口
 * @author ybjx
 * @date 2019/3/24 10:04
 */
@RestController
@RequestMapping("/api/article/tag")
public class ArticleTagController {

    /**
     * 标签服务
     */
    private final ArticleTagService tagService;

    @Autowired
    public ArticleTagController(ArticleTagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 删除文章标签
     * @param id 标签ID
     * @return 是否删除成功
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public PojoResult<Boolean> deleteTag(@PathVariable("id") Integer id){
        tagService.deleteArticleTag(id);

        return new PojoResult<>(true);
    }

    /**
     * 添加标签
     * @param tagDTO 标签信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public PojoResult<Boolean> addTag(@RequestBody ArticleTagDTO tagDTO){
        tagService.addArticleTag(tagDTO);
        return new PojoResult<>(true);
    }
}
