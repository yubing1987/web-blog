package com.ybjx.blog.dto;

import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.UpdateCheck;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 文章传输结构
 * Created by YuBing on 2018/11/19.
 */
public class ArticleDTO {

    /**
     * 主键
     */
    @NotNull(message = "文章ID不能为空", groups = {UpdateCheck.class})
    private Integer id;

    /**
     * 文章名称
     */
    @NotEmpty(message = "文章名称不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String title;

    /**
     * 文章图片
     */
    @NotEmpty(message = "文章图片不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String picture;

    /**
     * 文章摘要
     */
    @NotEmpty(message = "文章摘要不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String abstractContent;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后修改时间
     */
    private Date modifyDate;

    /**
     * 文章状态
     */
    private Boolean published;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    /**
     * 阅读数量
     */
    private Integer viewCount;

    /**
     * 文章内容
     */
    @NotEmpty(message = "文章内容不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String content;

    /**
     * 是不是草稿
     */
    private Boolean draft;

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
     * 获取文章状态
     *
     * @return published - 文章状态
     */
    public Boolean getPublished() {
        return published;
    }

    /**
     * 设置文章状态
     *
     * @param published 文章状态
     */
    public void setPublished(Boolean published) {
        this.published = published;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", picture='" + picture + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", published=" + published +
                ", isDeleted=" + isDeleted +
                ", viewCount=" + viewCount +
                ", content='" + content + '\'' +
                ", draft=" + draft +
                '}';
    }
}