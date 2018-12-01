package com.ybjx.blog.service;

import com.ybjx.blog.dao.RelateArticleMapper;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.RelateArticleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by YuBing at 2018/11/28
 */
@Service
public class RelateArticleService {

    private final RelateArticleMapper relateArticleMapper;

    @Autowired
    public RelateArticleService(RelateArticleMapper relateArticleMapper) {
        this.relateArticleMapper = relateArticleMapper;
    }

    public List<ArticleDO> getRelateArticle(String uuid){
        return relateArticleMapper.getRelateArticleList(uuid);
    }
}
