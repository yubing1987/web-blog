package com.ybjx.blog.dto;

import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.DeleteCheck;
import com.ybjx.blog.checker.group.UpdateCheck;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 分组信息
 * @author ybjx
 * @date 2019/5/1 22:07
 */
public class GroupDTO {
    /**
     * 主键
     */
    @NotNull(message = "分组ID不能为空", groups = {DeleteCheck.class, UpdateCheck.class})
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
     * 父group的id
     */
    @NotNull(message = "父分组的ID不能为空", groups = {CreateCheck.class})
    private Integer parentId;

    /**
     * 组名
     */
    @NotNull(message = "组名不能为空", groups = {CreateCheck.class})
    private String name;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    /**
     * 所有者ID
     */
    private Integer owner;

    /**
     * 文章分组图片
     */
    private String icon;

    /**
     * 分组类型
     */
    @NotNull(message = "分组类型不能为空", groups = {CreateCheck.class})
    private String type;

    /**
     * 子分组信息
     */
    private List<GroupDTO> children;

    /**
     * 分组中的文章列表
     */
    private List<ArticleDTO> articles;

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
     * 获取父group的id
     *
     * @return parent_id - 父group的id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父group的id
     *
     * @param parentId 父group的id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取组名
     *
     * @return name - 组名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组名
     *
     * @param name 组名
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取文章分组图片
     *
     * @return icon - 文章分组图片
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置文章分组图片
     *
     * @param icon 文章分组图片
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取分组类型
     *
     * @return type - 分组类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置分组类型
     *
     * @param type 分组类型
     */
    public void setType(String type) {
        this.type = type;
    }

    public List<GroupDTO> getChildren() {
        return children;
    }

    public void setChildren(List<GroupDTO> children) {
        this.children = children;
    }

    public List<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleDTO> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", owner=" + owner +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", children=" + children +
                ", articles=" + articles +
                '}';
    }
}
