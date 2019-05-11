package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleTagRefDTO;
import com.ybjx.blog.service.TagRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章标签相关的接口
 * @author ybjx
 * @date 2019/5/4 11:08
 */
@RestController
@RequestMapping("/api")
public class ArticleTagRefController {

    /**
     * 文章标签服务
     */
    private final TagRefService tagRefService;

    /**
     * 构造方法
     */
    @Autowired
    public ArticleTagRefController(TagRefService tagRefService) {
        this.tagRefService = tagRefService;
    }

    /**
     * 添加文章标签信息
     * @param tagRefDTO 标签信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/manage/tag/ref", method = RequestMethod.POST)
    public PojoResult<Boolean> addTagRef(ArticleTagRefDTO tagRefDTO){
        tagRefService.addTagRef(tagRefDTO);
        return new PojoResult<>(true);
    }

    /**
     * 删除文章标签
     * @param tagRefDTO 文章标签信息
     * @return 是否删除成功
     */
    @RequestMapping(value = "/manage/tag/ref/del", method = RequestMethod.POST)
    public PojoResult<Boolean> delTagRef(ArticleTagRefDTO tagRefDTO){
        tagRefService.delTagRef(tagRefDTO);
        return new PojoResult<>(true);
    }
}
