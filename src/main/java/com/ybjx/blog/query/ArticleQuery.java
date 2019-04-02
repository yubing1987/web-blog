package com.ybjx.blog.query;

/**
 * 文章查询信息
 * @author YuBing
 * @date 2019/2/15
 */
public class ArticleQuery extends BaseQuery {
    /**
     * 文章标签
     */
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ArticleQuery{" +
                "tag='" + tag + '\'' +
                "} " + super.toString();
    }
}
