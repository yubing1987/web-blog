package com.ybjx.blog.controller;

import com.ybjx.blog.common.result.ListResult;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleGroupRefDTO;
import com.ybjx.blog.service.ArticleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 分组中文章的相关操作
 * @author ybjx
 * @date 2019/5/11 11:35
 */
@RestController
@RequestMapping("/api")
public class ArticleGroupRefController {

    /**
     * 文章分组操作
     */
    private final ArticleGroupService articleGroupService;

    /**
     * 构造方法
     */
    @Autowired
    public ArticleGroupRefController(ArticleGroupService articleGroupService) {
        this.articleGroupService = articleGroupService;
    }

    /**
     * 添加分组文章
     * @param refDTO 分组文章信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/manage/group/ref", method = RequestMethod.POST)
    public PojoResult<Boolean> addGroupRef(@RequestBody ArticleGroupRefDTO refDTO){
        articleGroupService.addArticleGroupRef(refDTO);
        return new PojoResult<>(true);
    }

    /**
     * 删除分组文章
     * @param id 文章ID
     * @return 是否删除成功
     */
    @RequestMapping(value = "/manage/group/ref/{id}/del", method = RequestMethod.POST)
    public PojoResult<Boolean> delGroupRef(@PathVariable("id") Integer id){
        articleGroupService.delArticleGroupRef(id);
        return new PojoResult<>(true);
    }

    /**
     * 更新分组文章信息
     * @param refDTO 分组文章信息
     * @return 是否更新成功
     */
    @RequestMapping(value = "/manage/group/ref/edit", method = RequestMethod.POST)
    public PojoResult<Boolean> updateGroupRef(@RequestBody ArticleGroupRefDTO refDTO){
        articleGroupService.updateArticleGroupRef(refDTO);
        return new PojoResult<>(true);
    }

    /**
     * 获取分组信息tree
     * @param groupId 分组ID
     * @return 树型分组信息
     */
    @RequestMapping(value = "/group/ref/{groupId}/tree", method = RequestMethod.GET)
    public ListResult<ArticleGroupRefDTO> getArticleGroupTree(@PathVariable("groupId") Integer groupId){
        return new ListResult<>(articleGroupService.getGroupRefTree(groupId));
    }
}
