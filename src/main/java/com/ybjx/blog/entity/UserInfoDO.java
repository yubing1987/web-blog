package com.ybjx.blog.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息
 * @author ybjx
 */
@Table(name = "user_info")
public class UserInfoDO {
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
     * 是否被删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 外部账号类型
     */
    private String type;

    /**
     * 外部账号唯一标识符
     */
    @Column(name = "ref_id")
    private String refId;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 登录名称
     */
    @Column(name = "login_name")
    private String loginName;

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
     * 获取外部账号类型
     *
     * @return type - 外部账号类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置外部账号类型
     *
     * @param type 外部账号类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取外部账号唯一标识符
     *
     * @return ref_id - 外部账号唯一标识符
     */
    public String getRefId() {
        return refId;
    }

    /**
     * 设置外部账号唯一标识符
     *
     * @param refId 外部账号唯一标识符
     */
    public void setRefId(String refId) {
        this.refId = refId;
    }

    /**
     * 获取用户名称
     *
     * @return name - 用户名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名称
     *
     * @param name 用户名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户头像
     *
     * @return icon - 用户头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置用户头像
     *
     * @param icon 用户头像
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取账号密码
     *
     * @return password - 账号密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置账号密码
     *
     * @param password 账号密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取登录名称
     *
     * @return loginName - 登录名称
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名称
     *
     * @param loginName 登录名称
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDeleted=" + isDeleted +
                ", type='" + type + '\'' +
                ", refId='" + refId + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", password='" + password + '\'' +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}