package com.ybjx.blog.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章草稿数据
 * Created by YuBing on 2019/2/17.
 */
@Table(name = "article_draft")
public class ArticleDraftDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 草稿创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 草稿修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 是否已经被删除了
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 正文
     */
    private String content;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要
     */
    @Column(name = "abstract_content")
    private String abstractContent;

    /**
     * 文章封面图片
     */
    private String picture;

    /**
     * 文章ID
     */
    @Column(name = "article_id")
    private Integer articleId;

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
     * 获取草稿创建时间
     *
     * @return create_date - 草稿创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置草稿创建时间
     *
     * @param createDate 草稿创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取草稿修改时间
     *
     * @return modify_date - 草稿修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置草稿修改时间
     *
     * @param modifyDate 草稿修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取是否已经被删除了
     *
     * @return is_deleted - 是否已经被删除了
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否已经被删除了
     *
     * @param isDeleted 是否已经被删除了
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取正文
     *
     * @return content - 正文
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置正文
     *
     * @param content 正文
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文章摘要
     *
     * @return abstract_content - 文章摘要
     */
    public String getAbstractContent() {
        return abstractContent;
    }

    /**
     * 设置文章摘要
     *
     * @param abstractContent 文章摘要
     */
    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    /**
     * 获取文章封面图片
     *
     * @return picture - 文章封面图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置文章封面图片
     *
     * @param picture 文章封面图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
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
}