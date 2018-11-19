package com.ybjx.blog.common.result;


/**
 * 分页结果
 * Created by YuBing on 2018/11/19.
 */
public class PageResult<T> extends BaseResult<Page<T>> {

    /**
     * 无参构造函数
     */
    public PageResult(){
        super(null);
    }

    /**
     * 提供分页内容的构造函数
     * @param content 分页内容
     */
    public PageResult(Page<T> content) {
        super(content);
    }
}
