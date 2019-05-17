package com.ybjx.blog.service;

import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.UpdateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.dao.ArticleGroupRefMapper;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.dto.ArticleGroupRefDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.ArticleGroupRefDO;
import com.ybjx.blog.entity.ArticleGroupDO;
import com.ybjx.blog.entity.UserInfoDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 分组中的文章相关操作
 * @author ybjx
 * @date 2019/5/11 10:32
 */
@Service
public class ArticleGroupService {

    /**
     * 文章服务
     */
    private final ArticleService articleService;

    /**
     * 分组文章关联操作
     */
    private final ArticleGroupRefMapper groupRefMapper;

    /**
     * 分组服务
     */
    private final GroupService groupService;

    /**
     * 日志服务
     */
    private final OperationLogService logService;

    /**
     * 构造方法
     */
    @Autowired
    public ArticleGroupService(ArticleService articleService,
                               ArticleGroupRefMapper groupRefMapper,
                               GroupService groupService,
                               OperationLogService logService) {
        this.articleService = articleService;
        this.groupRefMapper = groupRefMapper;
        this.groupService = groupService;
        this.logService = logService;
    }

    /**
     * 给分组中添加文章
     * @param groupRefDTO 分组信息
     */
    @ParameterCheck(CreateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void addArticleGroupRef(ArticleGroupRefDTO groupRefDTO){
        UserInfoDO user = UserHolder.getUser();
        checkPermission(groupRefDTO);
        ArticleGroupRefDO groupRefDO = new ArticleGroupRefDO();
        groupRefDO.setArticleId(groupRefDTO.getArticleId());
        groupRefDO.setGroupId(groupRefDTO.getGroupId());
        groupRefDO.setParentId(groupRefDTO.getParentId());
        groupRefDO.setLevel(groupRefDTO.getLevel());
        if(groupRefDO.getLevel() == null){
            groupRefDO.setLevel(0);
        }
        groupRefDO.setCreateDate(new Date());
        groupRefDO.setModifyDate(new Date());
        groupRefDO.setIsDeleted(false);
        try{
            groupRefMapper.insert(groupRefDO);
            logService.addLog("分组中添加文章", groupRefDO.getId(), OperationTyp.ARTICLE_GROUP_REF, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存分组文章出错", e);
        }
    }

    /**
     * 删除分组中的文章
     * @param id 关联信息ID
     */
    public void delArticleGroupRef(int id){
        UserInfoDO user = UserHolder.getUser();
        ArticleGroupRefDO refDO = groupRefMapper.selectByPrimaryKey(id);
        if(refDO == null || refDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "关联信息没有找到");
        }

        ArticleGroupDO group = groupService.getGroupById(refDO.getGroupId());
        if(group != null && group.getIsDeleted()){
            if(!group.getOwner().equals(user.getId())){
                throw new BlogException(ErrorCode.PERMISSION_DENIED);
            }
        }

        ArticleGroupRefDO groupRefDO = new ArticleGroupRefDO();
        groupRefDO.setGroupId(refDO.getGroupId());
        groupRefDO.setParentId(refDO.getId());
        groupRefDO.setIsDeleted(false);
        if(groupRefMapper.selectCount(groupRefDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_DELETE_ERROR, "还存在子节点，无法直接删除");
        }

        refDO.setIsDeleted(true);
        refDO.setModifyDate(new Date());
        try{
            groupRefMapper.updateByPrimaryKey(refDO);
            logService.addLog("删除分组文章", refDO.getId(), OperationTyp.ARTICLE_GROUP_REF, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "更新分组文章信息出错", e);
        }
    }

    /**
     * 编辑分组文章信息
     * @param groupRefDTO 文章信息
     */
    @ParameterCheck(UpdateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleGroupRef(ArticleGroupRefDTO groupRefDTO){
        UserInfoDO user = UserHolder.getUser();
        ArticleGroupRefDO groupRefDO = groupRefMapper.selectByPrimaryKey(groupRefDTO.getId());
        if(groupRefDO == null || groupRefDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "分组信息没有找到");
        }
        ArticleGroupRefDTO refDTO = new ArticleGroupRefDTO();
        refDTO.setGroupId(groupRefDO.getGroupId());
        refDTO.setArticleId(groupRefDO.getArticleId());
        refDTO.setParentId(0);
        checkPermission(refDTO);
        groupRefDO.setLevel(groupRefDTO.getLevel());
        groupRefDO.setModifyDate(new Date());
        try{
            groupRefMapper.updateByPrimaryKeySelective(groupRefDO);
            logService.addLog("编辑分组中文章信息", groupRefDO.getId(), OperationTyp.ARTICLE_GROUP_REF, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存分组文章出错", e);
        }
    }

    /**
     * 获取分组详细树状信息
     * @param groupId 分组ID
     * @return 树状分组详情
     */
    public List<ArticleGroupRefDTO> getGroupRefTree(int groupId){
        ArticleGroupDO articleGroupDO = groupService.getGroupById(groupId);
        if(articleGroupDO == null || articleGroupDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "分组信息没有找到");
        }
        ArticleGroupRefDO groupRefDO = new ArticleGroupRefDO();
        groupRefDO.setIsDeleted(false);
        groupRefDO.setGroupId(groupId);
        List<ArticleGroupRefDO> list = groupRefMapper.select(groupRefDO);
        List<Integer> articleIds = new ArrayList<>();
        List<ArticleGroupRefDTO> result = new ArrayList<>();
        Map<Integer, List<ArticleGroupRefDTO>> map = new HashMap<>(8);
        for(ArticleGroupRefDO g: list){
            articleIds.add(g.getArticleId());
            ArticleGroupRefDTO groupRefDTO = new ArticleGroupRefDTO();
            BeanUtils.copyProperties(g, groupRefDTO);
            List<ArticleGroupRefDTO> children = map.computeIfAbsent(g.getId(), k -> new ArrayList<>());
            groupRefDTO.setChildren(children);
            if(g.getParentId() == 0){
                result.add(groupRefDTO);
            }
            else{
                List<ArticleGroupRefDTO> items = map.get(g.getParentId());
                if(items != null){
                    items.add(groupRefDTO);
                }
                else{
                    items = new ArrayList<>();
                    items.add(groupRefDTO);
                    map.put(g.getId(), items);
                }
            }
        }

        List<ArticleDO> articles = articleService.getArticleList(articleIds);
        Map<Integer, ArticleDTO> cache = new HashMap<>(8);
        for(ArticleDO a: articles){
            ArticleDTO ad = new ArticleDTO();
            BeanUtils.copyProperties(a, ad);
            cache.put(a.getId(), ad);
        }
        updateResult(result, cache);
        return result;
    }

    /**
     * 更新分组信息，排序和加入文章信息
     * @param list 分组列表
     * @param cache 文章id与文章信息的对应信息
     */
    private void updateResult(List<ArticleGroupRefDTO> list, Map<Integer, ArticleDTO> cache){
        list.sort((o1, o2) -> {
            if (o1.getLevel() < o2.getLevel()) {
                return -1;
            } else if (o1.getLevel().equals(o2.getLevel())) {
                return 0;
            } else {
                return 1;
            }
        });
        for(ArticleGroupRefDTO refDTO: list){
            refDTO.setArticle(cache.get(refDTO.getArticleId()));
            updateResult(refDTO.getChildren(), cache);
        }
    }

    /**
     * 校验分组信息权限
     * @param groupRefDTO 分组信息
     */
    private void checkPermission(ArticleGroupRefDTO groupRefDTO){
        UserInfoDO user = UserHolder.getUser();
        ArticleGroupDO articleGroupDO = groupService.getGroupById(groupRefDTO.getGroupId());
        if(articleGroupDO == null || articleGroupDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "分组信息没有找到");
        }
        ArticleDO articleDO = articleService.getArticleById(groupRefDTO.getArticleId());
        if(articleDO == null || articleDO.getIsDeleted()){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章信息没有找到");
        }
        if(groupRefDTO.getParentId() != 0){
            ArticleGroupRefDO temp = groupRefMapper.selectByPrimaryKey(groupRefDTO.getParentId());
            if( temp == null || temp.getIsDeleted()){
                throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "父节点没有找到");
            }
            if(!temp.getGroupId().equals(groupRefDTO.getGroupId())){
                throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "父节点不正确");
            }
        }
        if(!articleDO.getOwner().equals(user.getId()) || !articleGroupDO.getOwner().equals(user.getId())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED);
        }
    }
}
