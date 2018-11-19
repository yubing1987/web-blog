package com.ybjx.blog.common.result;

/**
 * 对象形式的返回结果
 * Created by YuBing on 2018/11/19.
 */
public class ObjectResult<T> extends BaseResult<T> {

    /**
     * 没有参数的构造函数
     */
    public ObjectResult(){
        super(null);
    }

    /**
     * 带内容的构造函数
     * @param content 返回结果
     */
    public ObjectResult(T content) {
        super(content);
    }
}
