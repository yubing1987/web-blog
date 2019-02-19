package com.ybjx.blog.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 关联文章信息
 * create by YuBing at 2019/2/20
 */
@Table(name = "article_related")
public class ArticleRelatedDO {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章1的ID
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 另一篇文章的ID
     */
    @Column(name = "other_article_id")
    private Integer otherArticleId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 最后修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 是否被删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 排序值
     */
    @Column(name = "order_value")
    private Integer orderValue;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文章1的ID
     *
     * @return article_id - 文章1的ID
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置文章1的ID
     *
     * @param articleId 文章1的ID
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取另一篇文章的ID
     *
     * @return other_article_id - 另一篇文章的ID
     */
    public Integer getOtherArticleId() {
        return otherArticleId;
    }

    /**
     * 设置另一篇文章的ID
     *
     * @param otherArticleId 另一篇文章的ID
     */
    public void setOtherArticleId(Integer otherArticleId) {
        this.otherArticleId = otherArticleId;
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

    /**
     * 获取最后修改时间
     *
     * @return modify_date - 最后修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置最后修改时间
     *
     * @param modifyDate 最后修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取是否被删除
     *
     * @return is_deleted - 是否被删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否被删除
     *
     * @param isDeleted 是否被删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取排序值
     *
     * @return order_value - 排序值
     */
    public Integer getOrderValue() {
        return orderValue;
    }

    /**
     * 设置排序值
     *
     * @param orderValue 排序值
     */
    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }
}