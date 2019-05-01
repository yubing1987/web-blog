package com.ybjx.blog.dto;

import com.ybjx.blog.checker.group.CreateCheck;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * 文章回复信息
 * @author ybjx
 * @date 2019/5/1 21:48
 */
public class ArticleCommentDTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后修改时间
     */
    private Date modifyDate;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    /**
     * 所有者ID
     */
    private Integer owner;

    /**
     * 回复内容
     */
    @NotEmpty(message = "回复内容不能为空", groups = {CreateCheck.class})
    private String content;

    /**
     * 关联回复的ID
     */
    @NotEmpty(message = "关联回复不能为空", groups = {CreateCheck.class})
    private Integer parentId;

    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 关联回复
     */
    private List<ArticleCommentDTO> children;

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
     * 获取回复内容
     *
     * @return content - 回复内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置回复内容
     *
     * @param content 回复内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取关联回复的ID
     *
     * @return parent_id - 关联回复的ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置关联回复的ID
     *
     * @param parentId 关联回复的ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public List<ArticleCommentDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleCommentDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ArticleCommentDTO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDeleted=" + isDeleted +
                ", owner=" + owner +
                ", content='" + content + '\'' +
                ", parentId=" + parentId +
                ", articleId=" + articleId +
                ", children=" + children +
                '}';
    }
}
