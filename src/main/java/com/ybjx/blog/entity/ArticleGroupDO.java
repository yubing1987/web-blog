package com.ybjx.blog.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 文章分组
 * @author ybjx
 */
@Table(name = "article_group")
public class ArticleGroupDO {
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
     * 组名
     */
    private String name;

    /**
     * 是否被删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 所有者ID
     */
    private Integer owner;

    /**
     * 文章分组图片
     */
    private String picture;

    /**
     * 分组描述
     */
    private String description;

    /**
     * 分组类型
     */
    private String type;

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
     * @return picture - 文章分组图片
     */
    public String getPicture() {
        return picture;
    }

    /**
     * 设置文章分组图片
     *
     * @param picture 文章分组图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取分组描述
     *
     * @return description - 分组描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置分组描述
     *
     * @param description 分组描述
     */
    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "ArticleGroupDO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                ", owner=" + owner +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}