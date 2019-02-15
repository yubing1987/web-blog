package com.ybjx.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ybjx.blog.checker.ParameterCheck;
import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.UpdateCheck;
import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.result.Page;
import com.ybjx.blog.common.result.PageResult;
import com.ybjx.blog.dao.ArticleMapper;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * 构造函数
     * @param articleMapper 注入文章数据库操作
     */
    @Autowired
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 添加文章
     * @param articleDTO 文章数据
     */
    @Transactional(rollbackFor = Exception.class)
    @ParameterCheck(CreateCheck.class)
    public void addArticle(ArticleDTO articleDTO) {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setTitle(articleDTO.getTitle());
        articleDO.setIsDeleted(false);
        articleDO = articleMapper.selectOne(articleDO);
        if (articleDO != null) {
            throw new BlogException(ErrorCode.OBJECT_EXIST, "文章名称已经被使用过了");
        }

        articleDO = new ArticleDO();
        // 拷贝属性
        BeanUtils.copyProperties(articleDTO, articleDO);
        // 把剩余的属性设置为默认值
        articleDO.setIsDeleted(false);
        articleDO.setCreateDate(new Date());
        articleDO.setModifyDate(new Date());
        articleDO.setViewCount(0);
        articleDO.setId(null);
        articleDO.setPublished(false);
        // 保存文章记录
        try {
            articleMapper.insert(articleDO);
        } catch (Exception e) {
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存文章出错", e);
        }
    }

    /**
     * 通过文章ID查询文章信息
     * @param id 文章ID
     * @return 文章信息
     */
    public ArticleDO getArticleById(Integer id) {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(id);
        articleDO.setIsDeleted(false);
        articleDO = articleMapper.selectOne(articleDO);
        return articleDO;
    }

    /**
     * 更新文章基本信息
     * @param articleDTO 文章信息
     */
    @Transactional(rollbackFor = Exception.class)
    @ParameterCheck(UpdateCheck.class)
    public void editArticle(ArticleDTO articleDTO) {
        ArticleDO articleDO = articleMapper.selectByPrimaryKey(articleDTO.getId());
        if (articleDO == null || articleDO.getIsDeleted()) {
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章没有找到");
        }
        articleDO.setTitle(articleDTO.getTitle());
        articleDO.setPicture(articleDTO.getPicture());
        articleDO.setContent(articleDTO.getContent());
        articleDO.setAbstractContent(articleDTO.getAbstractContent());
        articleDO.setModifyDate(new Date());
        try {
            articleMapper.updateByPrimaryKeySelective(articleDO);
        } catch (Exception e) {
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, e);
        }
    }

    /**
     * 发表文章
     * @param articleId 文章ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void articlePublished(int articleId, boolean published) {
        ArticleDO articleDO = articleMapper.selectByPrimaryKey(articleId);
        if (articleDO == null || articleDO.getIsDeleted()) {
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章没有找到");
        }
        if (articleDO.getPublished() == published) {
            if (articleDO.getPublished()) {
                throw new BlogException(ErrorCode.OBJECT_STATUS_ERROR, "文章已经发表过了");
            } else {
                throw new BlogException(ErrorCode.OBJECT_STATUS_ERROR, "文章还没有发表");
            }
        }
        articleDO.setModifyDate(new Date());
        articleDO.setPublished(published);
        try {
            articleMapper.updateByPrimaryKeySelective(articleDO);
        } catch (Exception e) {
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, e);
        }
    }

    /**
     * 删除文章
     * @param articleId 文章ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(int articleId) {
        ArticleDO articleDO = articleMapper.selectByPrimaryKey(articleId);
        if (articleDO == null || articleDO.getIsDeleted()) {
            throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "文章没有找到");
        }
        if (articleDO.getPublished()) {
            throw new BlogException(ErrorCode.OBJECT_STATUS_ERROR, "文章已经发表过了");
        }
        articleDO.setModifyDate(new Date());
        articleDO.setIsDeleted(true);
        try {
            articleMapper.updateByPrimaryKeySelective(articleDO);
        } catch (Exception e) {
            throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, e);
        }
    }

    /**
     * 查询文章列表
     * @param page 页码
     * @param size 每一页大小
     * @param key 搜索关键字
     * @return 查找的结果
     */
    public PageResult<ArticleDTO> queryArticle(int page, int size, String key){
        PageHelper.startPage(page, size, "modify_date desc");
        List<ArticleDO> list = articleMapper.queryArticle(key);
        PageInfo<ArticleDO> pageInfo = new PageInfo<>(list);
        List<ArticleDTO> items = new ArrayList<>();
        // 拷贝数据
        for(ArticleDO article: list){
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            items.add(articleDTO);
        }

        // 构造返回值
        PageResult<ArticleDTO> result = new PageResult<>();
        Page<ArticleDTO> p = new Page<>();
        result.setContent(p);
        p.setItems(items);
        p.setCurrent(page);
        p.setLimit(size);
        p.setTotal(pageInfo.getTotal());
        return result;
    }
}
