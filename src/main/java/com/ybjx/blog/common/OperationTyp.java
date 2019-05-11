package com.ybjx.blog.common;

/**
 * @author ybjx
 * @date 2019/5/3 9:59
 */
public enum OperationTyp {
    /**
     * 操作文章
     */
    ARTICLE,
    /**
     * 文章标签
     */
    ARTICLE_TAG,
    /**
     * 文章标签关联信息
     */
    ARTICLE_TAG_REF,
    /**
     * 文章分组
     */
    GROUP,
    /**
     * 分组中的文章操作
     */
    ARTICLE_GROUP_REF
}
