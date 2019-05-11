package com.ybjx.blog.dto;

import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.DeleteCheck;
import com.ybjx.blog.checker.group.UpdateCheck;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 文章分组信息
 * @author ybjx
 */
public class ArticleGroupRefDTO {
    /**
     * 主键
     */
    @NotNull(message = "分组信息ID不能为空", groups = {DeleteCheck.class, UpdateCheck.class})
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
     * 文章ID
     */
    @NotNull(message = "文章ID不能为空", groups = {CreateCheck.class})
    private Integer articleId;

    /**
     * 文章分组ID
     */
    @NotNull(message = "文章分组ID不能为空", groups = {CreateCheck.class})
    private Integer groupId;

    /**
     * 文章排序
     */
    @NotNull(message = "文章分组ID不能为空", groups = {UpdateCheck.class})
    private Integer level;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 子节点
     */
    private List<ArticleGroupRefDTO> children;

    /**
     * 对应的文章信息
     */
    private ArticleDTO article;

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
    /**
     * 获取父节点ID
     *
     * @return parent_id - 父节点ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父节点ID
     *
     * @param parentId 父节点ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<ArticleGroupRefDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ArticleGroupRefDTO> children) {
        this.children = children;
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public void setArticle(ArticleDTO article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "ArticleGroupRefDTO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDeleted=" + isDeleted +
                ", articleId=" + articleId +
                ", groupId=" + groupId +
                ", level=" + level +
                ", parentId=" + parentId +
                ", children=" + children +
                ", article=" + article +
                '}';
    }
}