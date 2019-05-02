package com.ybjx.blog.filter;

import com.ybjx.blog.common.Constant;
import com.ybjx.blog.common.CookieUtil;
import com.ybjx.blog.common.UserHolder;
import com.ybjx.blog.common.UserTokenManager;
import com.ybjx.blog.entity.UserInfoDO;
import com.ybjx.blog.service.UserService;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户过滤器
 * @author ybjx
 * @date 2019/5/1 23:15
 */
public class UserFilter extends MatchFilter {

    /**
     * 登录页面URL
     */
    private final static String LOGIN_URL = "/login";

    /**
     * 用户相关的服务
     */
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }

    /**
     * 过滤函数
     * @param servletRequest -
     * @param servletResponse -
     * @param filterChain -
     * @throws IOException -
     * @throws ServletException -
     */
    @Override
    public void filter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserHolder.cleanUser();

        String userToken = CookieUtil.getCookieValue(request, Constant.USER_TOKEN_COOKIE_NAME);

        if(StringUtils.isEmpty(userToken)){
            response.sendRedirect(LOGIN_URL);
            return;
        }
        Integer userId = UserTokenManager.getUserId(userToken);
        if(userId == null){
            CookieUtil.addCookie(response, Constant.USER_TOKEN_COOKIE_NAME, null);
            response.sendRedirect(LOGIN_URL);
            return;
        }

        UserInfoDO user = userService.getUser(userId);
        if(user == null){
            CookieUtil.addCookie(response, Constant.USER_TOKEN_COOKIE_NAME, null);
            response.sendRedirect(LOGIN_URL);
            return;
        }

        UserHolder.setUser(user);
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {
            UserHolder.cleanUser();
        }

    }
}
