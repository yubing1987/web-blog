package com.ybjx.blog.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户token管理
 * @author ybjx
 * @date 2019/5/1 23:35
 */
public class UserTokenManager {

    /**
     * 用户缓存信息
     */
    private static class User {
        /**
         * 用户id
         */
        private Integer userId;

        /**
         * 过期时间
         */
        private long expire;

        Integer getUserId() {
            return userId;
        }

        void setUserId(Integer userId) {
            this.userId = userId;
        }

        long getExpire() {
            return expire;
        }

        void setExpire(long expire) {
            this.expire = expire;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", expire=" + expire +
                    '}';
        }
    }

    /**
     * 用户Token
     */
    private static Map<String, User> userTokens = new ConcurrentHashMap<>();

    /**
     * 通过token获取用户ID
     * @param token user token
     * @return 用户ID
     */
    public static Integer getUserId(String token){
        User user =  userTokens.get(token);
        if(user == null){
            return null;
        }
        if(user.getExpire() > System.currentTimeMillis()){
            user.setExpire(System.currentTimeMillis() + 30 * 60 * 1000);
            return user.getUserId();
        }
        else{
            userTokens.remove(token);
            return null;
        }
    }

    /**
     * 删除用户token
     * @param token 用户token
     */
    public static void removeToken(String token){
        userTokens.remove(token);
    }

    /**
     * 添加用户ID
     * @param token user token
     * @param userId 用户ID
     */
    public static void addUserToken(String token, Integer userId){
        User user = new User();
        user.setUserId(userId);
        user.setExpire(System.currentTimeMillis() + 30 * 60 * 1000);
        userTokens.put(token, user);
    }
}
