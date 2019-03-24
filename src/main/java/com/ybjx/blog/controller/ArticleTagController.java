package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleTagDTO;
import com.ybjx.blog.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
     * 添加标签
     * @param tags 标签信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public PojoResult<Boolean> updateTags(@PathVariable("id") Integer articleId,
                                          @RequestBody List<String> tags){
        if(tags == null){
            tags = new ArrayList<>();
        }
        Set<String> set = new HashSet<>();
        List<ArticleTagDTO> oldTags = tagService.getArticleTagList(articleId);
        for(ArticleTagDTO tag: oldTags){
            if(!tags.contains(tag.getTag())){
                tagService.deleteArticleTag(tag.getId());
            }
            else{
                set.add(tag.getTag());
            }
        }
        for(String tag: tags){
            if(!set.contains(tag)){
                tagService.addArticleTag(articleId, tag);
            }
        }

        return new PojoResult<>(true);
    }
}
