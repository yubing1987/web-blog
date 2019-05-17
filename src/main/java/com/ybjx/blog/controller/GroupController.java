package com.ybjx.blog.controller;

import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.dto.ArticleGroupDTO;
import com.ybjx.blog.query.GroupQuery;
import com.ybjx.blog.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章分组相关的接口
 * @author ybjx
 * @date 2019/5/11 10:11
 */
@RestController
@RequestMapping("/api")
public class GroupController {

    /**
     * 分组相关的业务功能
     */
    private final GroupService groupService;

    /**
     * 构造方法
     */
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 添加分组
     * @param group 分组信息
     * @return 是否添加成功
     */
    @RequestMapping(value = "/manage/group", method = RequestMethod.POST)
    PojoResult<Boolean> addGroup(@RequestBody ArticleGroupDTO group){
        groupService.addGroup(group);
        return new PojoResult<>(true);
    }

    /**
     * 删除分组
     * @param id 分组ID
     * @return 是否删除成功
     */
    @RequestMapping(value = "/manage/group/{id}/del", method = RequestMethod.POST)
    PojoResult<Boolean> delGroup(@PathVariable("id") Integer id){
        groupService.delGroup(id);
        return new PojoResult<>(true);
    }

    @RequestMapping(value = "/manage/group/edit", method = RequestMethod.POST)
    PojoResult<Boolean> editGroup(@RequestBody ArticleGroupDTO group){
        groupService.editGroup(group);
        return new PojoResult<>(true);
    }

    /**
     * 管理端查询文章
     * @param query 查询条件
     * @return 查询到的分组列表
     */
    @RequestMapping(value = "/manage/group/list", method = RequestMethod.GET)
    PageResult<ArticleGroupDTO> managerQueryGroup(GroupQuery query){
        return groupService.getGroupList(query, UserHolder.getUser().getId());
    }

    /*
     * *************************************************************************************************************
     * 以下接口为无需校验权限的接口
     * *************************************************************************************************************
     */
    /**
     * 普通用户查询文章列表
     * @param query 查询条件
     * @return 查询到的文章列表
     */
    @RequestMapping(value = "/group/list", method = RequestMethod.GET)
    PageResult<ArticleGroupDTO> queryGroup(GroupQuery query){
        return groupService.getGroupList(query, null);
    }
}
