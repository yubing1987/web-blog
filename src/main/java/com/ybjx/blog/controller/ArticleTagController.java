package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.ListResult;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleTagDTO;
import com.ybjx.blog.entity.ArticleTagDO;
import com.ybjx.blog.query.ArticleTagQuery;
import com.ybjx.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签相关的接口
 * @author ybjx
 * @date 2019/5/3 12:07
 */
@RestController
@RequestMapping("/api")
public class ArticleTagController {

    /**
     * 标签服务
     */
    private final TagService tagService;

    /**
     * 构造方法
     */
    @Autowired
    public ArticleTagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 添加tag
     * @param tagDTO tag信息
     * @return 操作结果
     */
    @RequestMapping(value = "/manage/tag", method = RequestMethod.POST)
    public PojoResult<Boolean> addTag(ArticleTagDTO tagDTO){
        tagService.addTag(tagDTO);
        return new PojoResult<>(true);
    }

    /**
     * 通过标签ID删除标签
     * @param tagId 标签ID
     * @return 删除结果
     */
    @RequestMapping(value = "/manage/tag/{tagId}/del", method = RequestMethod.POST)
    public PojoResult<Boolean> delTag(@PathVariable("tagId") Integer tagId){
        tagService.delTag(tagId);
        return new PojoResult<>(true);
    }

    /**
     * 编辑标签
     * @param tagDTO 标签信息
     * @return 编辑结果
     */
    @RequestMapping(value = "/manage/tag/edit", method = RequestMethod.POST)
    public PojoResult<Boolean> editTag(ArticleTagDTO tagDTO){
        tagService.updateTag(tagDTO);
        return new PojoResult<>(true);
    }

    /**
     * 查找标签列表
     * @return 查找到的标签信息
     */
    @RequestMapping(value = "/manage/tag/", method = RequestMethod.GET)
    public ListResult<ArticleTagDTO> getTagList(){
        List<ArticleTagDO> list = tagService.getTagList(null, null);
        List<ArticleTagDTO> tagList = new ArrayList<>();
        for(ArticleTagDO tagDO: list){
            ArticleTagDTO tagDTO = new ArticleTagDTO();
            BeanUtils.copyProperties(tagDO, tagDTO);
            tagList.add(tagDTO);
        }
        return new ListResult<>(tagList);
    }
}
