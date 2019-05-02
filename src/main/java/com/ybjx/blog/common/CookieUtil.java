package com.ybjx.blog.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie相关的操作
 * @author ybjx
 * @date 2019/5/1 23:05
 */
public class CookieUtil {

    /**
     * 添加cookie
     * @param response http响应
     * @param name cookie名称
     * @param value cookie值
     */
    public static void addCookie( HttpServletResponse response, String name, String value){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(-1);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setVersion(0);
        response.addCookie(cookie);
    }

    /**
     * 通过cookie的名称查找值
     * @param request http请求
     * @param name cookie名称
     * @return cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String name){
        if(request == null){
            return null;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        for(Cookie cookie: request.getCookies()){
            if(cookie.getName().equals(name)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
