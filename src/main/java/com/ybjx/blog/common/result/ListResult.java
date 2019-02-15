package com.ybjx.blog.common.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表形式的返回值
 * @param <T> 返回值对象
 * Created by YuBing on 2018/11/19.
 */
public class ListResult<T> extends BaseResult {

    /**
     * 列表信息
     */
    private List<T> content = new ArrayList<>();

    /**
     * 无参构造函数
     */
    public ListResult() {
    }

    /**
     * 提供内容的构造函数
     * @param content 返回值内容
     */
    public ListResult(List<T> content) {
        if (content != null) {
            this.content.addAll(content);
        }
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content.clear();
        if (content != null) {
            this.content.addAll(content);
        }
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "content=" + content +
                "} " + super.toString();
    }
}
