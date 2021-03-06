package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleTagDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 文章标签
 * @author ybjx
 */
public interface ArticleTagMapper extends Mapper<ArticleTagDO> {
    /**
     * 通过文章ID查找全部的标签信息
     * @param articleIds 文章ID
     * @return 标签信息
     */
    List<ArticleTagDO> getTagByArticleIds(@Param("articleIds") List<Integer> articleIds);

    /**
     * 查询标签列表
     * @param ids 标签ID列表
     * @param userId 所有者ID
     * @param key 搜索关键字
     * @return 查找到的标签列表
     */
    List<ArticleTagDO> getTagList(@Param("ids") List<Integer> ids,
                                  @Param("userId") Integer userId,
                                  @Param("key") String key);
}