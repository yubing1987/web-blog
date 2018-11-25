package com.ybjx.blog.service;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.config.ArticleConfig;
import com.ybjx.blog.dao.ArticleMapper;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.store.IFileStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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
     * 文章内容存储服务
     */
    private final IFileStore fileStore;

    /**
     * 构造函数
     * @param articleMapper 注入文章数据库操作
     */
    @Autowired
    public ArticleService(ArticleMapper articleMapper, IFileStore fileStore) {
        this.articleMapper = articleMapper;
        this.fileStore = fileStore;
    }

    /**
     * 添加文章
     * @param articleDTO 文章数据
     */
    public void addArticle(ArticleDTO articleDTO) {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setName(articleDTO.getName());
        articleDO.setIsDeleted(false);
        articleDO = articleMapper.selectOne(articleDO);
        if (articleDO != null) {
            throw new BlogException(ErrorCode.OBJECT_EXIST, "文章名称已经被使用过了");
        }

        articleDO = new ArticleDO();
        BeanUtils.copyProperties(articleDTO, articleDO);

        articleDO.setIsDeleted(false);
        articleDO.setCreateDate(new Date());
        articleDO.setModifyDate(new Date());
        articleDO.setStatus("new");
        articleDO.setUuid(UUID.randomUUID().toString());
        // 保存文章内容
        fileStore.save(articleDO.getUuid(), articleDTO.getContent());
        // 保存文章记录
        try {
            articleMapper.insert(articleDO);
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存文章出厂");
        }
    }
}
