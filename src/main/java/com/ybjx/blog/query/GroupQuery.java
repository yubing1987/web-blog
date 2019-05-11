package com.ybjx.blog.query;

/**
 * 分组信息查询条件
 * @author ybjx
 * @date 2019/5/11 9:57
 */
public class GroupQuery extends BaseQuery {

    /**
     * 分组类型
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GroupQuery{" +
                "type='" + type + '\'' +
                "} " + super.toString();
    }
}
