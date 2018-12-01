package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.RelateArticleDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RelateArticleMapper extends Mapper<RelateArticleDO> {

    List<ArticleDO> getRelateArticleList(@Param("uuid") String uuid);
}