package com.ybjx.blog.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 文章分组信息
 * @author ybjx
 */
@Table(name = "article_group_ref")
public class ArticleGroupRefDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 文章ID
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 文章分组ID
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 文章排序
     */
    private Integer level;

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
     * 获取文章ID
     *
     * @return article_id - 文章ID
     */
    public Integer getArticleId() {
        return articleId;
    }

    /**
     * 设置文章ID
     *
     * @param articleId 文章ID
     */
    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取文章分组ID
     *
     * @return group_id - 文章分组ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置文章分组ID
     *
     * @param groupId 文章分组ID
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取文章排序
     *
     * @return level - 文章排序
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置文章排序
     *
     * @param level 文章排序
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ArticleGroupRefDO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDeleted=" + isDeleted +
                ", articleId=" + articleId +
                ", groupId=" + groupId +
                ", level=" + level +
                '}';
    }
}