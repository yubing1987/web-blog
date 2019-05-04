package com.ybjx.blog.service;

import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.DeleteCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.dao.ArticleTagRefMapper;
import com.ybjx.blog.dto.ArticleTagRefDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.ArticleTagDO;
import com.ybjx.blog.entity.ArticleTagRefDO;
import com.ybjx.blog.entity.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文章标签关联信息相关的功能
 * @author ybjx
 * @date 2019/5/4 10:49
 */
@Service
public class TagRefService {

    /**
     * 标签服务
     */
    private final TagService tagService;

    /**
     * 文章服务
     */
    private final ArticleService articleService;

    /**
     * 文章标签关联服务
     */
    private final ArticleTagRefMapper tagRefMapper;

    /**
     * 日志相关操作
     */
    private final OperationLogService logService;


    /**
     * 构造方法
     */
    @Autowired
    public TagRefService(TagService tagService,
                         ArticleService articleService,
                         ArticleTagRefMapper tagRefMapper,
                         OperationLogService logService) {
        this.tagService = tagService;
        this.articleService = articleService;
        this.tagRefMapper = tagRefMapper;
        this.logService = logService;
    }

    /**
     * 添加文章标签
     * @param tagRefDTO 关联信息
     */
    @ParameterCheck(CreateCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void addTagRef(ArticleTagRefDTO tagRefDTO){
        ArticleDO articleDO = articleService.getArticleById(tagRefDTO.getArticleId());
        ArticleTagDO tagDO = tagService.getTagById(tagRefDTO.getTagId());

        UserInfoDO user = UserHolder.getUser();
        if(!user.getId().equals(articleDO.getOwner()) || !user.getId().equals(tagDO.getOwner())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限给文章【"
                            + articleDO.getId() + "】添加标签【" + tagDO.getId() + "】"));
        }

        ArticleTagRefDO tagRefDO = new ArticleTagRefDO();
        tagRefDO.setTagId(tagDO.getId());
        tagRefDO.setIsDeleted(false);
        tagRefDO.setArticleId(articleDO.getId());

        if(tagRefMapper.selectCount(tagRefDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_EXIST, "标签已经添加过了");
        }

        tagRefDO.setCreateDate(new Date());
        tagRefDO.setModifyDate(new Date());
        try{
            tagRefMapper.insert(tagRefDO);
            logService.addLog("添加文章标签", tagRefDO.getId(), OperationTyp.ARTICLE_TAG_REF, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存文章标签信息出粗", e);
        }
    }

    /**
     * 删除文章标签关联关系
     * @param tagRefDTO 文章标签关联关系
     */
    @ParameterCheck(DeleteCheck.class)
    @Transactional(rollbackFor = Exception.class)
    public void delTagRef(ArticleTagRefDTO tagRefDTO){
        ArticleDO articleDO = articleService.getArticleById(tagRefDTO.getArticleId());
        ArticleTagDO tagDO = tagService.getTagById(tagRefDTO.getTagId());

        UserInfoDO user = UserHolder.getUser();
        if(!user.getId().equals(articleDO.getOwner()) || !user.getId().equals(tagDO.getOwner())){
            throw new BlogException(ErrorCode.PERMISSION_DENIED,
                    new Exception(user.getId() + "无权限给文章【"
                            + articleDO.getId() + "】添加标签【" + tagDO.getId() + "】"));
        }
        ArticleTagRefDO tagRefDO = new ArticleTagRefDO();
        tagRefDO.setTagId(tagDO.getId());
        tagRefDO.setIsDeleted(false);
        tagRefDO.setArticleId(articleDO.getId());
        List<ArticleTagRefDO> list = tagRefMapper.select(tagRefDO);

        if(list.size() == 0){
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章标签关联关系没有找到");
        }
        try{
            tagRefDO = list.get(0);
            tagRefDO.setIsDeleted(true);
            tagRefDO.setModifyDate(new Date());
            tagRefMapper.updateByPrimaryKey(tagRefDO);
            logService.addLog("删除文章标签", tagRefDO.getId(), OperationTyp.ARTICLE_TAG_REF, user.getId());
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "保存文章标签信息出错", e);
        }
    }

    /**
     * 通过文章ID查询文章所有的标签信息
     * @param articleId 文章ID
     * @return 标签信息
     */
    public List<ArticleTagRefDO> getArticleTag(Integer articleId){
        List<Integer> list = new ArrayList<>();
        list.add(articleId);
        return tagRefMapper.getTagByArticleIds(list);
    }
}
