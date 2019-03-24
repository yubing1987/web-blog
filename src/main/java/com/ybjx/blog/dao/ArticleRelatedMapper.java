package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleRelatedDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 关联文章相关的数据库操作
 */
public interface ArticleRelatedMapper extends Mapper<ArticleRelatedDO> {
    /**
     * 通过文章ID，查询文章的关联文章
     * @param articleId 文章ID
     * @return 关联文章信息
     */
    List<ArticleRelatedDO> getRelatedArticle(@Param("articleId") Integer articleId);

    /**
     * 删除文件全部的关联文章
     * @param articleId 文章ID
     */
    void deleteArticleRelated(@Param("articleId") Integer articleId);
}