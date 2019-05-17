package com.ybjx.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.PagingCheck;
import com.ybjx.blog.checker.group.UpdateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.common.result.Page;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.dao.ArticleGroupMapper;
import com.ybjx.blog.dto.ArticleGroupDTO;
import com.ybjx.blog.entity.ArticleGroupDO;
import com.ybjx.blog.entity.UserInfoDO;
import com.ybjx.blog.query.GroupQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章分组相关功能
 * @author ybjx
 * @date 2019/5/4 13:25
 */
@Service
public class GroupService {

    /**
     * 分组数据库操作
     */
    private final ArticleGroupMapper articleGroupMapper;

    /**
     * 日志服务
     */
    private final OperationLogService logService;

    /**
     * 构造方法
     */
    @Autowired
    public GroupService(ArticleGroupMapper articleGroupMapper,
                        OperationLogService logService) {
        this.articleGroupMapper = articleGroupMapper;
        this.logService = logService;
    }

    /**
     * 添加文章分组
     * @param articleGroupDTO 分组信息
     */
    @ParameterCheck(CreateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void addGroup(ArticleGroupDTO articleGroupDTO){
        ArticleGroupDO articleGroupDO = new ArticleGroupDO();
        articleGroupDO.setIsDeleted(false);
        articleGroupDO.setName(articleGroupDTO.getName());
        if(articleGroupMapper.selectCount(articleGroupDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_EXIST, "名称已经被使用过了");
        }
        BeanUtils.copyProperties(articleGroupDTO, articleGroupDO);
        articleGroupDO.setId(null);
        articleGroupDO.setIsDeleted(false);
        articleGroupDO.setModifyDate(new Date());
        articleGroupDO.setCreateDate(new Date());
        articleGroupDO.setOwner(UserHolder.getUser().getId());
        try{
            articleGroupMapper.insert(articleGroupDO);
            logService.addLog("添加文章分组", articleGroupDO.getId(), OperationTyp.GROUP, UserHolder.getUser().getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存分组信息出错", e);
        }
    }

    /**
     * 删除文章分组
     * @param groupId 分组ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delGroup(int groupId){
        ArticleGroupDO articleGroupDO = articleGroupMapper.selectByPrimaryKey(groupId);
        if(articleGroupDO == null || articleGroupDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章分组没有找到");
        }
        UserInfoDO user = UserHolder.getUser();
        if(!user.getId().equals(articleGroupDO.getOwner())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限删除文章分组【" + groupId + "】"));
        }
        try{
            articleGroupDO.setIsDeleted(true);
            articleGroupDO.setModifyDate(new Date());
            articleGroupMapper.updateByPrimaryKey(articleGroupDO);
            logService.addLog("删除文章分组", articleGroupDO.getId(), OperationTyp.GROUP, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_DELETE_ERROR, "删除文章分组出错", e);
        }
    }

    /**
     * 编辑文章分组信息
     * @param group 文章分组信息
     */
    @Transactional(rollbackFor = Exception.class)
    @ParameterCheck({UpdateCheck.class})
    public void editGroup(ArticleGroupDTO group){
        ArticleGroupDO groupDO = articleGroupMapper.selectByPrimaryKey(group.getId());
        if(groupDO == null || groupDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章分组没有找到");
        }
        UserInfoDO user = UserHolder.getUser();
        if(!user.getId().equals(groupDO.getOwner())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限删除文章分组【" + groupDO.getId() + "】"));
        }
        if(!StringUtils.isEmpty(group.getDescription())){
            groupDO.setDescription(group.getDescription());
        }
        if(!StringUtils.isEmpty(group.getPicture())){
            groupDO.setPicture(group.getPicture());
        }
        if(!StringUtils.isEmpty(group.getName()) && !group.getName().equals(groupDO.getName())){
            ArticleGroupDO temp = new ArticleGroupDO();
            temp.setIsDeleted(false);
            temp.setName(group.getName());
            if(articleGroupMapper.selectCount(temp) > 0){
                throw new BlogException(ErrorCode.OBJECT_EXIST, "名称已经被使用过了");
            }
            groupDO.setName(group.getName());
        }
        groupDO.setModifyDate(new Date());

        try{
            articleGroupMapper.updateByPrimaryKey(groupDO);
            logService.addLog("修改文章分组", groupDO.getId(), OperationTyp.GROUP, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "保存分组信息出错", e);
        }
    }

    /**
     * 分页查找文章分组信息
     * @param query 查询条件
     * @param userId 所有者ID
     * @return 查询到的文章列表
     */
    @ParameterCheck(PagingCheck.class)
    public PageResult<ArticleGroupDTO> getGroupList(GroupQuery query, Integer userId){
        PageHelper.startPage(query.getPage(), query.getSize(), "modify_date desc");
        List<ArticleGroupDO> list = articleGroupMapper.queryGroupList(userId, query.getType(), query.getKey());
        PageResult<ArticleGroupDTO> result = new PageResult<>();
        Page<ArticleGroupDTO> p = new Page<>();
        result.setContent(p);

        PageInfo<ArticleGroupDO> page = new PageInfo<>(list);
        p.setTotal(page.getTotal());
        p.setLimit(query.getSize());
        p.setCurrent(query.getPage());

        List<ArticleGroupDTO> groups = new ArrayList<>();
        for(ArticleGroupDO g: list){
            ArticleGroupDTO group = new ArticleGroupDTO();
            BeanUtils.copyProperties(g, group);
            groups.add(group);
        }
        p.setItems(groups);

        return result;
    }

    /**
     * 通过分组ID查询分组信息
     * @param id 分组ID
     * @return 分组信息
     */
    public ArticleGroupDO getGroupById(Integer id){
        return articleGroupMapper.selectByPrimaryKey(id);
    }
}
