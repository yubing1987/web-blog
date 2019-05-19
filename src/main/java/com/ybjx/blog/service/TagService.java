package com.ybjx.blog.service;

import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.UpdateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.dao.ArticleTagMapper;
import com.ybjx.blog.dao.ArticleTagRefMapper;
import com.ybjx.blog.dto.ArticleTagDTO;
import com.ybjx.blog.entity.ArticleTagDO;
import com.ybjx.blog.entity.ArticleTagRefDO;
import com.ybjx.blog.entity.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 文章标签相关的功能
 * @author ybjx
 * @date 2019/5/3 11:21
 */
@Service
public class TagService {

    /**
     * 标签数据库操作
     */
    private final ArticleTagMapper tagMapper;

    /**
     * 文章标签关联关系操作
     */
    private final ArticleTagRefMapper tagRefMapper;

    /**
     * 操作日志服务
     */
    private final OperationLogService logService;

    /**
     * 构造方法
     */
    @Autowired
    public TagService(ArticleTagMapper tagMapper,
                      ArticleTagRefMapper tagRefMapper,
                      OperationLogService logService) {
        this.tagMapper = tagMapper;
        this.tagRefMapper = tagRefMapper;
        this.logService = logService;
    }

    /**
     * 添加标签
     * @param tagDTO 标签信息
     */
    @ParameterCheck(CreateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void addTag(ArticleTagDTO tagDTO){
        UserInfoDO user = UserHolder.getUser();
        ArticleTagDO tagDO = new ArticleTagDO();
        tagDO.setIsDeleted(false);
        tagDO.setOwner(user.getId());
        tagDO.setTag(tagDTO.getTag());
        if(tagMapper.selectCount(tagDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_EXIST, "该标签已经添加过了");
        }
        tagDO.setModifyDate(new Date());
        tagDO.setCreateDate(new Date());
        try{
            tagMapper.insert(tagDO);
            logService.addLog("添加标签", tagDO.getId(), OperationTyp.ARTICLE_TAG, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存标签出错", e);
        }
    }

    /**
     * 删除标签
     * @param tagId 标签ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void delTag(int tagId){
        ArticleTagDO tagDO = tagMapper.selectByPrimaryKey(tagId);
        if(tagDO == null || tagDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "标签没有找到");
        }
        UserInfoDO user = UserHolder.getUser();
        if(!tagDO.getOwner().equals(user.getId())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限删除标签【" + tagDO.getId() + "】"));
        }
        ArticleTagRefDO refDO = new ArticleTagRefDO();
        refDO.setIsDeleted(false);
        refDO.setTagId(tagDO.getId());
        if(tagRefMapper.selectCount(refDO) > 0){
            throw new BlogException(ErrorCode.STATUS_ERROR, "标签正在被使用，无法删除");
        }
        try{
            tagDO.setIsDeleted(true);
            tagDO.setModifyDate(new Date());
            tagMapper.updateByPrimaryKey(tagDO);
            logService.addLog("删除标签", tagDO.getId(), OperationTyp.ARTICLE_TAG, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_DELETE_ERROR, "删除标签出错", e);
        }
    }

    /**
     * 修改标签
     * @param tagDTO 标签内容
     */
    @ParameterCheck(UpdateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void updateTag(ArticleTagDTO tagDTO){
        ArticleTagDO tagDO = tagMapper.selectByPrimaryKey(tagDTO.getId());
        if(tagDO == null || tagDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "标签没有找到");
        }
        UserInfoDO user = UserHolder.getUser();
        if(!tagDO.getOwner().equals(user.getId())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限删除标签【" + tagDO.getId() + "】"));
        }

        if(tagDTO.getTag() != null && !tagDTO.getTag().equals(tagDO.getTag())){
            ArticleTagDO temp = new ArticleTagDO();
            temp.setIsDeleted(false);
            temp.setOwner(user.getId());
            temp.setTag(tagDTO.getTag());
            if(tagMapper.selectCount(temp) > 0){
                throw new BlogException(ErrorCode.OBJECT_EXIST, "该标签已经添加过了");
            }
            tagDO.setTag(tagDTO.getTag());
            tagDO.setModifyDate(new Date());
            try{
                tagMapper.updateByPrimaryKey(tagDO);
                logService.addLog("修改标签：【" + temp.getTag() + "】-->【" + tagDO.getTag() + "】",
                        tagDO.getId(), OperationTyp.ARTICLE_TAG, user.getId());
            }
            catch (Exception e){
                throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "保存标签出错");
            }
        }
    }

    /**
     * 通过ID列表查询所有的标签，如果标签为空，就返回全部
     * @param ids 标签ID
     * @param key 搜索关键字
     * @return 标签雷彪
     */
    public List<ArticleTagDO> getTagList(String key, List<Integer> ids, Integer userId){
        if(ids != null && ids.size() == 0){
            ids = null;
        }
        return tagMapper.getTagList(ids, userId, key);
    }

    /**
     * 通过标签ID查询标签信息
     * @param tagId 标签ID
     * @return 标签信息
     */
    public ArticleTagDO getTagById(Integer tagId){
        ArticleTagDO tagDO = tagMapper.selectByPrimaryKey(tagId);
        if(tagDO == null){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "标签没有找到！");
        }
        return tagDO;
    }
}
