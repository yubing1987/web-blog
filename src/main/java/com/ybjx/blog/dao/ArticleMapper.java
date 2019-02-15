package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 文章数据库操作类
 * Created by YuBing on 2018/11/19.
 */
public interface ArticleMapper extends Mapper<ArticleDO> {

    List<ArticleDO> queryArticle(@Param("key") String key);
}