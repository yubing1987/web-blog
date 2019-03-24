package com.ybjx.blog.dto;

import com.ybjx.blog.checker.group.CreateCheck;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ybjx
 * @date 2019/3/24 9:38
 */
public class ArticleTagDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 文章ID
     */
    @NotNull(message = "文章ID不能为空", groups = {CreateCheck.class})
    private Integer articleId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后修改时间
     */
    private Date modifyDate;

    /**
     * 文章标签
     */
    @NotBlank(message = "文章标签不能为空", groups = {CreateCheck.class})
    private String tag;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "ArticleTagDTO{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", tag='" + tag + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
