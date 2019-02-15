package com.ybjx.blog.common.result;

/**
 * 对象形式的返回结果
 * @param <T> 返回值对象
 * Created by YuBing on 2018/11/19.
 */
public class PojoResult<T> extends BaseResult {
    /**
     * 返回的结果内容
     */
    private T content;
    /**
     * 没有参数的构造函数
     */
    public PojoResult() {

    }

    /**
     * 带内容的构造函数
     * @param content 返回结果
     */
    public PojoResult(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PojoResult{" +
                "content=" + content +
                "} " + super.toString();
    }
}
