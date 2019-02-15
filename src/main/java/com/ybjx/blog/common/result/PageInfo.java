package com.ybjx.blog.common.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回值的分页内容
 * @param <T> 返回值对象
 * Created by YuBing on 2018/11/19.
 */
public class PageInfo<T> implements Serializable {
    /**
     * 内容
     */
    private List<T> items = new ArrayList<>();

    /**
     * 当前页
     */
    private int current;

    /**
     * 总数
     */
    private int total;

    /**
     * 每一页大小
     */
    private int limit;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items.clear();
        if (items != null) {
            this.items.addAll(items);
        }
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "items=" + items +
                ", current=" + current +
                ", total=" + total +
                '}';
    }
}
