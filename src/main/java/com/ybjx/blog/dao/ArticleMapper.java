package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 文章信息
 * @author ybjx
 */
public interface ArticleMapper extends Mapper<ArticleDO> {

    /**
     * 查询文章列表
     * @param userId 文章所有者ID
     * @param tagId 标签ID
     * @param key 搜索关键字
     * @return 文章列表
     */
    List<ArticleDO> getArticleList(@Param("userId") Integer userId,
                                          @Param("tagId") Integer tagId,
                                          @Param("key") String key);

    /**
     * 通过ID列表查询文章信息
     * @param articleIds 文章ID列表
     * @return 文章列表
     */
    List<ArticleDO> getArticlesByIds(@Param("articleIds") List<Integer> articleIds);
}