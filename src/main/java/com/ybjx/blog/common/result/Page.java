package com.ybjx.blog.common.result;

/**
 * 分页返回值的分页内容
 * Created by YuBing on 2018/11/19.
 */
public class Page<T> {
    /**
     * 内容
     */
    private T content;

    /**
     * 当前页
     */
    private int current;

    /**
     * 总数
     */
    private int total;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "content=" + content +
                ", current=" + current +
                ", total=" + total +
                '}';
    }
}
