package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 文章数据库操作类
 * Created by YuBing on 2018/11/19.
 */
public interface ArticleMapper extends Mapper<ArticleDO> {

    /**
     * 查找文章列表
     * @param key 搜索关键字
     * @return 查找到的文章列表
     */
    List<ArticleDO> queryArticle(@Param("key") String key, @Param("onlyPublish") Boolean onlyPublish);

    /**
     * 通过文章ID数组查询文章列表
     * @param ids 文章ID 数组
     * @return 文章列表
     */
    List<ArticleDO> queryArticleByIds(@Param("ids") Collection<Integer> ids);

    /**
     * 查找不在这个id列表里的文章信息
     * @param ids id列表
     * @param key 查询关键字
     * @return 文章列表
     */
    List<ArticleDO> queryArticleNotInIds(@Param("ids") Collection<Integer> ids,
                                         @Param("key") String key);
}