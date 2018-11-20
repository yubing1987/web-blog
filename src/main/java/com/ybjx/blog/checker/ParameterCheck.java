package com.ybjx.blog.checker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数合法性校验注解
 * create by YuBing at 2018/11/20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterCheck {

    /**
     * 参数校验分类group
     * @return group列表
     */
    Class[] value();
}
