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
import com.ybjx.blog.dao.ArticleDraftMapper;
import com.ybjx.blog.dao.ArticleMapper;
import com.ybjx.blog.dao.ArticleRelatedMapper;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleDO;
import com.ybjx.blog.entity.ArticleDraftDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 文章服务
 * @author ybjx
 * @date 2018/11/20.
 */
@Service
public class ArticleService {

    /**
     * 文章数据库操作
     */
    private final ArticleMapper articleMapper;

    /**
     * 文章草稿数据库操作
     */
    private final ArticleDraftMapper articleDraftMapper;

    /**
     * 文章标签服务
     */
    private final ArticleTagService tagService;

    /**
     * 关联文章服务
     */
    private final ArticleRelatedMapper relatedMapper;

    /**
     * 构造函数
     * @param articleMapper 注入文章数据库操作
     */
    @Autowired
    public ArticleService(ArticleMapper articleMapper,
                          ArticleDraftMapper articleDraftMapper,
                          ArticleTagService tagService,
                          ArticleRelatedMapper relatedMapper) {
        this.articleMapper = articleMapper;
        this.articleDraftMapper = articleDraftMapper;
        this.tagService = tagService;
        this.relatedMapper = relatedMapper;
    }

    /**
     * 添加文章
     * @param articleDTO 文章数据
     */
    @Transactional(rollbackFor = Exception.class)
    @ParameterCheck(CreateCheck.class)
    public ArticleDO addArticle(ArticleDTO articleDTO) {
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
            return articleDO;
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
        try {
            articleDO = articleMapper.selectOne(articleDO);
            return articleDO;
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.SYSTEM_ERROR, "获取文章出错", e);
        }
    }

    /**
     * 通过文章ID查找草稿信息
     * @param id 文章ID
     * @return 草稿信息
     */
    public ArticleDraftDO getArticleDraft(Integer id) {
        ArticleDraftDO articleDraftDO = new ArticleDraftDO();
        articleDraftDO.setIsDeleted(false);
        articleDraftDO.setArticleId(id);
        return articleDraftMapper.selectOne(articleDraftDO);
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
        try {
            if (!articleDO.getPublished()) {
                // 如果文章还没有发表，就直接更新文章
                articleDO.setTitle(articleDTO.getTitle());
                articleDO.setPicture(articleDTO.getPicture());
                articleDO.setContent(articleDTO.getContent());
                articleDO.setAbstractContent(articleDTO.getAbstractContent());
                articleDO.setModifyDate(new Date());
                articleMapper.updateByPrimaryKeySelective(articleDO);
            } else {
                // 如果文章已经发表过了，就存放到草稿里去
                ArticleDraftDO draftDO = getArticleDraft(articleDTO.getId());
                if (draftDO == null) {
                    // 还没有这篇文章的草稿，就新建一个草稿
                    draftDO = new ArticleDraftDO();
                    BeanUtils.copyProperties(articleDTO, draftDO);
                    draftDO.setArticleId(articleDTO.getId());
                    draftDO.setCreateDate(new Date());
                    draftDO.setModifyDate(new Date());
                    draftDO.setId(null);
                    draftDO.setArticleId(articleDTO.getId());
                    articleDraftMapper.insert(draftDO);
                } else {
                    // 已经存在草稿了，就更新草稿信息
                    draftDO.setTitle(articleDTO.getTitle());
                    draftDO.setAbstractContent(articleDTO.getAbstractContent());
                    draftDO.setContent(articleDTO.getContent());
                    draftDO.setPicture(articleDTO.getPicture());
                    draftDO.setModifyDate(new Date());
                    articleDraftMapper.updateByPrimaryKey(draftDO);
                }
            }
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
        articleDO.setModifyDate(new Date());
        articleDO.setPublished(published);
        try {
            ArticleDraftDO draftDO = getArticleDraft(articleDO.getId());
            if (draftDO != null) {
                // 如果存在草稿信息，就把草稿同步到正文
                articleDO.setTitle(draftDO.getTitle());
                articleDO.setContent(draftDO.getContent());
                articleDO.setAbstractContent(draftDO.getAbstractContent());
                articleDO.setPicture(draftDO.getPicture());

                // 删除草稿
                draftDO.setIsDeleted(true);
                draftDO.setModifyDate(new Date());
                articleDraftMapper.updateByPrimaryKey(draftDO);
            }
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
        try {
            // 删除正文
            articleDO.setModifyDate(new Date());
            articleDO.setIsDeleted(true);
            articleMapper.updateByPrimaryKeySelective(articleDO);
            // 删除草稿
            ArticleDraftDO draftDO = new ArticleDraftDO();
            draftDO.setArticleId(articleDO.getId());
            draftDO.setIsDeleted(true);
            articleDraftMapper.updateByPrimaryKeySelective(draftDO);
            // 删除标签
            tagService.deleteArticleAllTag(articleId);
            // 删除关联文章ID
            relatedMapper.deleteArticleRelated(articleId);
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
    public PageResult<ArticleDTO> queryArticle(int page, int size, String key, Boolean onlyPublish) {
        PageHelper.startPage(page, size, "modify_date desc");
        List<ArticleDO> list = articleMapper.queryArticle(key, onlyPublish);
        PageInfo<ArticleDO> pageInfo = new PageInfo<>(list);
        List<ArticleDTO> items = new ArrayList<>();
        // 拷贝数据
        for (ArticleDO article: list) {
            ArticleDTO articleDTO = new ArticleDTO();
            article.setContent(null);
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

    /**
     * 通过文章ID列表查询文章信息
     * @param ids ID列表
     * @return 文章信息
     */
    List<ArticleDTO> queryArticle(Collection<Integer> ids) {
        List<ArticleDO> list = articleMapper.queryArticleByIds(ids);
        return copyArticle(list);
    }

    /**
     * 查询不在ID列表里的文章信息
     * @param ids id列表
     * @param key 检索关键字
     * @return 文章列表
     */
    List<ArticleDTO> queryArticleNotIn(Collection<Integer> ids, String key){
        List<ArticleDO> list = articleMapper.queryArticleNotInIds(ids, key);
        return copyArticle(list);
    }

    /**
     * 拷贝数据
     * @param list do数据
     * @return 拷贝后的dto数据
     */
    private List<ArticleDTO> copyArticle(List<ArticleDO> list){
        List<ArticleDTO> items = new ArrayList<>();
        // 拷贝数据
        for (ArticleDO article: list) {
            ArticleDTO articleDTO = new ArticleDTO();
            items.add(articleDTO);
            article.setContent(null);
            BeanUtils.copyProperties(article, articleDTO);
        }
        return items;
    }
}
