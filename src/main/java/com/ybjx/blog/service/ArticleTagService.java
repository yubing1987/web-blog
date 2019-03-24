package com.ybjx.blog.service;

import com.google.common.collect.Lists;
import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.ObjectUtils;
import com.ybjx.blog.dao.ArticleTagMapper;
import com.ybjx.blog.dto.ArticleTagDTO;
import com.ybjx.blog.entity.ArticleTagDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 文章标签服务
 * @author ybjx
 * @date 2019/3/24 9:35
 */
@Service
public class ArticleTagService {

    /**
     * 标签数据库操作
     */
    private final ArticleTagMapper tagMapper;

    @Autowired
    public ArticleTagService(ArticleTagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    /**
     * 通过文章ID获取该文章全部的标签
     * @param articleId 文章ID
     * @return 文章标签
     */
    public List<ArticleTagDTO> getArticleTagList(int articleId){
        ArticleTagDO tag = new ArticleTagDO();
        tag.setArticleId(articleId);
        tag.setIsDeleted(false);
        try{
            List<ArticleTagDO> list = tagMapper.select(tag);
            return ObjectUtils.listCopy(list, ArticleTagDTO.class);
        }
        catch (Exception e){
            return Lists.newArrayList();
        }
    }

    /**
     * 添加标签信息
     * @param tag 标签信息
     */
    @Transactional(rollbackFor = Exception.class)
    @ParameterCheck({CreateCheck.class})
    public void addArticleTag(ArticleTagDTO tag){
        ArticleTagDO tagDO = new ArticleTagDO();

        tagDO.setArticleId(tag.getArticleId());
        tagDO.setTag(tag.getTag());
        tagDO.setIsDeleted(false);

        if(tagMapper.selectCount(tagDO) > 0){
            throw new BlogException(ErrorCode.OBJECT_EXIST, "标签已经添加过了");
        }
        tagDO.setCreateDate(new Date());
        tagDO.setModifyDate(new Date());
        try{
            tagMapper.insert(tagDO);
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存标签出错", e);
        }
    }

    /**
     * 通过ID删除标签
     * @param id 标签ID
     */
    public void deleteArticleTag(int id){
        try{
            ArticleTagDO tagDO = new ArticleTagDO();
            tagDO.setId(id);
            tagDO.setIsDeleted(true);
            tagMapper.updateByPrimaryKeySelective(tagDO);
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "删除标签出错", e);
        }
    }

    /**
     * 删除文章全部标签
     * @param articleId 文章ID
     */
    void deleteArticleAllTag(int articleId){
        try{
            tagMapper.deleteAllTag(articleId);
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "删除标签出错", e);
        }
    }
}
