package com.ybjx.blog.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 操作日志
 * @author ybjx
 * @date 2019/5/3 9:58
 */
@Table(name = "operation_log")
public class OperationLogDO {
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
     * 是否被删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 日志内容
     */
    private String message;

    /**
     * 操作者
     */
    private Integer operator;

    /**
     * 被操作对象ID
     */
    @Column(name = "target_id")
    private Integer targetId;

    /**
     * 被操作对象的类型
     */
    @Column(name = "target_type")
    private String targetType;

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
     * 获取日志内容
     *
     * @return message - 日志内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置日志内容
     *
     * @param message 日志内容
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取操作者
     *
     * @return operator - 操作者
     */
    public Integer getOperator() {
        return operator;
    }

    /**
     * 设置操作者
     *
     * @param operator 操作者
     */
    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    /**
     * 获取被操作对象ID
     *
     * @return target_id - 被操作对象ID
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * 设置被操作对象ID
     *
     * @param targetId 被操作对象ID
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * 获取被操作对象的类型
     *
     * @return target_type - 被操作对象的类型
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置被操作对象的类型
     *
     * @param targetType 被操作对象的类型
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @Override
    public String toString() {
        return "OperationLogDO{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", isDeleted=" + isDeleted +
                ", message='" + message + '\'' +
                ", operator=" + operator +
                ", targetId=" + targetId +
                ", targetType='" + targetType + '\'' +
                '}';
    }
}