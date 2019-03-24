package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleTagDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ArticleTagMapper extends Mapper<ArticleTagDO> {

    /**
     * 删除文章全部的标签
     * @param articleId 文章ID
     */
    void deleteAllTag(@Param("articleId") Integer articleId);
}