package com.ybjx.blog.common;

import com.ybjx.blog.entity.UserInfoDO;

/**
 * 用户信息存放
 * @author ybjx
 * @date 2019/5/1 23:12
 */
public class UserHolder {
    /**
     * 用户信息
     */
    private static ThreadLocal<UserInfoDO> user = new ThreadLocal<>();

    /**
     * 保存用户信息
     * @param u 用户信息
     */
    public static void setUser(UserInfoDO u){
        user.set(u);
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    public static UserInfoDO getUser(){
        return user.get();
    }

    /**
     * 清空用户信息
     */
    public static void cleanUser(){
        user.remove();
    }
}
