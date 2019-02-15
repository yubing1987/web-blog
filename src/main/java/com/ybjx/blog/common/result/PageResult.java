package com.ybjx.blog.common.result;


/**
 * 分页结果
 * @param <T> 返回值对象
 * Created by YuBing on 2018/11/19.
 */
public class PageResult<T> extends BaseResult {

    private PageInfo<T> content;

    /**
     * 无参构造函数
     */
    public PageResult() {

    }

    /**
     * 提供分页内容的构造函数
     * @param content 分页内容
     */
    public PageResult(PageInfo<T> content) {
        this.content = content;
    }

    public PageInfo<T> getContent() {
        return content;
    }

    public void setContent(PageInfo<T> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "content=" + content +
                "} " + super.toString();
    }
}
