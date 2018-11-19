package com.ybjx.blog.dto;

import java.util.Date;

/**
 * 文章传输结构
 * Created by YuBing on 2018/11/19.
 */
public class ArticleDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 文章名称
     */
    private String name;

    /**
     * 文章图片
     */
    private String picture;

    /**
     * 文章描述
     */
    private String description;

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
    private String status;

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
     * @return name - 文章名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文章名称
     *
     * @param name 文章名称
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取文章描述
     *
     * @return description - 文章描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文章描述
     *
     * @param description 文章描述
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return status - 文章状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置文章状态
     *
     * @param status 文章状态
     */
    public void setStatus(String status) {
        this.status = status;
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
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", status='" + status + '\'' +
                '}';
    }
}