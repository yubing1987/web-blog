package com.ybjx.blog.dto;

import java.util.Date;

/**
 * 文章标签数据传输结构
 * Created by YuBing on 2018/11/19.
 */
public class ArticleTagDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 文章标签
     */
    private String tag;

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
     * 获取修改时间
     *
     * @return modify_date - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
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
     * 获取文章标签
     *
     * @return tag - 文章标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置文章标签
     *
     * @param tag 文章标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ArticleTagDO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", articleId=" + articleId +
                ", tag='" + tag + '\'' +
                '}';
    }
}