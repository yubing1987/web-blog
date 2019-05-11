package com.ybjx.blog.service;

import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.dao.GroupMapper;
import com.ybjx.blog.dto.GroupDTO;
import com.ybjx.blog.entity.GroupDO;
import com.ybjx.blog.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
    private final GroupMapper groupMapper;

    /**
     * 日志服务
     */
    private final OperationLogService logService;

    /**
     * 构造方法
     */
    @Autowired
    public GroupService(GroupMapper groupMapper,
                        OperationLogService logService) {
        this.groupMapper = groupMapper;
        this.logService = logService;
    }

    /**
     * 添加文章分组
     * @param groupDTO 分组信息
     */
    @ParameterCheck(CreateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void addGroup(GroupDTO groupDTO){
        GroupDO groupDO = new GroupDO();
        groupDO.setIsDeleted(false);
        groupDO.setName(groupDTO.getName());
        if(groupMapper.selectCount(groupDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_EXIST, "名称已经被使用过了");
        }
        BeanUtils.copyProperties(groupDTO, groupDO);
        groupDO.setId(null);
        groupDO.setIsDeleted(false);
        groupDO.setModifyDate(new Date());
        groupDO.setCreateDate(new Date());
        groupDO.setOwner(UserHolder.getUser().getId());
        try{
            groupMapper.insert(groupDO);
            logService.addLog("添加文章分组", groupDO.getId(), OperationTyp.GROUP, UserHolder.getUser().getId());
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
        GroupDO groupDO = groupMapper.selectByPrimaryKey(groupId);
        if(groupDO == null || groupDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章分组没有找到");
        }
        UserInfoDO user = UserHolder.getUser();
        if(!user.getId().equals(groupDO.getOwner())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限删除文章分组【" + groupId + "】"));
        }
        try{
            groupDO.setIsDeleted(true);
            groupDO.setModifyDate(new Date());
            groupMapper.updateByPrimaryKey(groupDO);
            logService.addLog("删除文章分组", groupDO.getId(), OperationTyp.GROUP, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_DELETE_ERROR, "删除文章分组出错", e);
        }
    }
}