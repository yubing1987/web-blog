package com.ybjx.blog.service;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.dao.ArticleRelatedMapper;
import com.ybjx.blog.dto.ArticleDTO;
import com.ybjx.blog.entity.ArticleRelatedDO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 关联文章相关的服务
 * create by YuBing at 2019/2/19
 */
@Service
public class ArticleRelatedService {

    /**
     * 关联文章的数据库操作
     */
    private final ArticleRelatedMapper relateMapper;

    /**
     * 文章服务
     */
    private final ArticleService articleService;

    public ArticleRelatedService(ArticleRelatedMapper relateMapper,
                                 ArticleService articleService) {
        this.relateMapper = relateMapper;
        this.articleService = articleService;
    }

    /**
     * 创建文章关联关系
     * @param articleId1 文章ID
     * @param articleId2 文章ID
     */
    public void createReleation(int articleId1, int articleId2) {
        int id1 = articleId1 > articleId2 ? articleId1 : articleId2;
        int id2 = articleId1 > articleId2 ? articleId2 : articleId1;
        ArticleRelatedDO relateDO = new ArticleRelatedDO();
        relateDO.setIsDeleted(false);
        relateDO.setArticleId(id1);
        relateDO.setOtherArticleId(id2);

        if (relateMapper.selectOne(relateDO) != null) {
            return;
        }

        relateDO.setCreateDate(new Date());
        relateDO.setModifyDate(new Date());
        relateDO.setOrderValue(0);
        try {
            relateMapper.insert(relateDO);
        } catch (Exception e) {
            throw new BlogException(ErrorCode.DATABASE_INSERT, "保存关联文章出错", e);
        }
    }

    /**
     * 删除关联文章信息
     * @param articleId1 文章ID
     * @param articleId2 文章ID
     */
    public void deletedRelation(int articleId1, int articleId2) {
        int id1 = articleId1 > articleId2 ? articleId1 : articleId2;
        int id2 = articleId1 > articleId2 ? articleId2 : articleId1;
        ArticleRelatedDO relateDO = new ArticleRelatedDO();
        relateDO.setArticleId(id1);
        relateDO.setOtherArticleId(id2);
        relateDO.setIsDeleted(false);
        try {
            relateDO = relateMapper.selectOne(relateDO);
            if (relateDO == null) {
                throw new BlogException(ErrorCode.OBJECT_NOT_FOUND, "关联文章没有找到");
            }
            relateDO.setIsDeleted(true);
            relateDO.setModifyDate(new Date());
            relateMapper.updateByPrimaryKey(relateDO);
        } catch (Exception e) {
            if (!(e instanceof BlogException)) {
                throw new BlogException(ErrorCode.OBJECT_UPDATE_ERROR, "删除关联文章出错", e);
            }
        }
    }

    /**
     * 通过文章ID，查询文章的关联文章
     * @param articleId 文章ID
     * @return 文章关联文章信息
     */
    public List<ArticleDTO> getRelatedArticle(Integer articleId) {
        List<ArticleRelatedDO> list = relateMapper.getRelatedArticle(articleId);
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }
        HashSet<Integer> set = new HashSet<>();
        for (ArticleRelatedDO relatedDO: list) {
            set.add(relatedDO.getArticleId());
            set.add(relatedDO.getOtherArticleId());
        }
        set.remove(articleId);
        List<ArticleDTO> articles = articleService.queryArticle(set);

        if (articles == null || articles.size() == 0) {
            return new ArrayList<>();
        }

        HashMap<Integer, ArticleDTO> map = new HashMap<>();
        List<ArticleDTO> result = new ArrayList<>();
        for (ArticleDTO articleDTO: articles) {
            map.put(articleDTO.getId(), articleDTO);
        }
        for (ArticleRelatedDO relatedDO: list) {
            ArticleDTO temp = map.get(relatedDO.getArticleId());
            if (temp != null) {
                result.add(temp);
            } else {
                temp = map.get(relatedDO.getOtherArticleId());
                if (temp != null) {
                    result.add(temp);
                }
            }
        }
        return result;
    }

    /**
     * 查询未关联的文章列表
     * @param articleId 文章ID
     * @param key 检索关键字
     * @return 文章列表
     */
    public List<ArticleDTO> getUnrelatedArticle(Integer articleId, String key){
        List<ArticleRelatedDO> list = relateMapper.getRelatedArticle(articleId);
        HashSet<Integer> set = new HashSet<>();
        for (ArticleRelatedDO relatedDO: list) {
            set.add(relatedDO.getArticleId());
            set.add(relatedDO.getOtherArticleId());
        }
        set.add(articleId);
        return articleService.queryArticleNotIn(set, key);
    }
}
