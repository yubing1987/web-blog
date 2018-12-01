package com.ybjx.blog.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "relate_article")
public class RelateArticleDO {
    /**
     * 主键
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章uuid
     */
    @Column(name = "article_uuid")
    private String articleUuid;

    /**
     * 关联的文章uuid
     */
    @Column(name = "relate_article_uuid")
    private String relateArticleUuid;

    /**
     * 排序标记
     */
    private Integer order;

    /**
     * 是否已经被删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章uuid
     *
     * @return article_uuid - 文章uuid
     */
    public String getArticleUuid() {
        return articleUuid;
    }

    /**
     * 设置文章uuid
     *
     * @param articleUuid 文章uuid
     */
    public void setArticleUuid(String articleUuid) {
        this.articleUuid = articleUuid;
    }

    /**
     * 获取关联的文章uuid
     *
     * @return relate_article_uuid - 关联的文章uuid
     */
    public String getRelateArticleUuid() {
        return relateArticleUuid;
    }

    /**
     * 设置关联的文章uuid
     *
     * @param relateArticleUuid 关联的文章uuid
     */
    public void setRelateArticleUuid(String relateArticleUuid) {
        this.relateArticleUuid = relateArticleUuid;
    }

    /**
     * 获取排序标记
     *
     * @return order - 排序标记
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置排序标记
     *
     * @param order 排序标记
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 获取是否已经被删除
     *
     * @return is_deleted - 是否已经被删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否已经被删除
     *
     * @param isDeleted 是否已经被删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}