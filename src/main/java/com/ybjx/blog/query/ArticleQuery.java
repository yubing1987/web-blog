package com.ybjx.blog.query;

/**
 * 文章查询信息
 * @author YuBing
 * @date 2019/2/15
 */
public class ArticleQuery extends BaseQuery {

    /**
     * 作者ID
     */
    private Integer userId;

    /**
     * 标签ID
     */
    private Integer tagId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleQuery{" +
                "userId=" + userId +
                ", tagId=" + tagId +
                "} " + super.toString();
    }
}
