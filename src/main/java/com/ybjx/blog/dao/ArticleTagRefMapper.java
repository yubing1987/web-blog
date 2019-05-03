package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleTagRefDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 文章标签信息
 * @author ybjx
 */
public interface ArticleTagRefMapper extends Mapper<ArticleTagRefDO> {
    /**
     * 通过文章ID查找全部的标签信息
     * @param articleIds 文章ID
     * @return 标签信息
     */
    List<ArticleTagRefDO> getTagByArticleIds(@Param("articleIds") List<Integer> articleIds);
}