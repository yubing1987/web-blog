package com.ybjx.blog.dto;

import com.ybjx.blog.checker.group.CreateCheck;
import com.ybjx.blog.checker.group.DeleteCheck;
import com.ybjx.blog.checker.group.UpdateCheck;

import javax.validation.constraints.NotEmpty;
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
    @NotNull(message = "标签ID不能为空", groups = {DeleteCheck.class, UpdateCheck.class})
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
     * 文章标签
     */
    @NotEmpty(message = "标签内容不能为空", groups = {CreateCheck.class, UpdateCheck.class})
    private String tag;

    /**
     * 是否被删除
     */
    private Boolean isDeleted;

    /**
     * 所有者ID
     */
    private Integer owner;

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

    @Override
    public String toString() {
        return "ArticleTagDO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", tag='" + tag + '\'' +
                ", isDeleted=" + isDeleted +
                ", owner=" + owner +
                '}';
    }
}
