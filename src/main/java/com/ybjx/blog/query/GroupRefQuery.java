package com.ybjx.blog.query;

import com.ybjx.blog.checker.group.QueryCheck;

import javax.validation.constraints.NotNull;

/**
 * @author ybjx
 * @date 2019/5/18 17:14
 */
public class GroupRefQuery extends BaseQuery {

    /**
     * 分组ID
     */
    @NotNull(message = "分组ID不能为空", groups = {QueryCheck.class})
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupRefQuery{" +
                "groupId=" + groupId +
                "} " + super.toString();
    }
}
