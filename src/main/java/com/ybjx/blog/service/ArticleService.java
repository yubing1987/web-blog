package com.ybjx.blog.service;

import com.ybjx.blog.config.ArticleConfig;
import com.ybjx.blog.dao.ArticleMapper;
import com.ybjx.blog.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文章服务
 * Created by YuBing on 2018/11/20.
 */
@Service
public class ArticleService {

    /**
     * 文章数据库操作
     */
    private final ArticleMapper articleMapper;

    /**
     * 文章相关的配置
     */
    private final ArticleConfig articleConfig;

    /**
     * 构造函数
     * @param articleMapper 注入文章数据库操作
     */
    @Autowired
    public ArticleService(ArticleMapper articleMapper, ArticleConfig articleConfig) {
        this.articleMapper = articleMapper;
        this.articleConfig = articleConfig;
    }

    /**
     * 添加文章
     * @param articleDTO 文章数据
     */
    public void addArticle(ArticleDTO articleDTO) {

    }
}
