package com.ybjx.blog.common.result;

import java.util.List;

/**
 * 列表形式的返回值
 * @param <T> 返回值对象
 * Created by YuBing on 2018/11/19.
 */
public class ListResult<T> extends BaseResult<List<T>> {

    /**
     * 无参构造函数
     */
    public ListResult() {
        super(null);
    }

    /**
     * 提供内容的构造函数
     * @param content 返回值内容
     */
    public ListResult(List<T> content) {
        super(content);
    }
}
