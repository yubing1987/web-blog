package com.ybjx.blog.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 文章信息
 * @author ybjx
 */
@Table(name = "article")
public class ArticleDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章名称
     */
    private String title;

    /**
     * 文章图片
     */
    private String picture;

    /**
     * 文章摘要
     */
    @Column(name = "abstract_content")
    private String abstractContent;

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
     * 阅读数量
     */
    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * 所有者ID
     */
    private Integer owner;

    /**
     * 文章内容
     */
    private String content;

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
     * 获取文章名称
     *
     * @return title - 文章名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章名称
     *
     * @param title 文章名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文章图片
     *
     * @return picture - 文章图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置文章图片
     *
     * @param picture 文章图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
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
     * 获取阅读数量
     *
     * @return view_count - 阅读数量
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置阅读数量
     *
     * @param viewCount 阅读数量
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取所有者ID
     *
     * @return owner - 所有者ID
     */
    public Integer getOwner() {
        return owner;
    }

    /**
     * 设置所有者ID
     *
     * @param owner 所有者ID
     */
    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleDO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDeleted=" + isDeleted +
                ", viewCount=" + viewCount +
                ", owner=" + owner +
                ", content='" + content + '\'' +
                '}';
    }
}