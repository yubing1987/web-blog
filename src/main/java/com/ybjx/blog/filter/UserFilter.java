package com.ybjx.blog.filter;

import com.ybjx.blog.common.*;
import com.ybjx.blog.config.LoginConfig;
import com.ybjx.blog.entity.UserInfoDO;
import com.ybjx.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static String LOGIN_URL = "/login?back_url=";

    private static Logger LOGGER = LoggerFactory.getLogger(UserFilter.class);

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
        UserInfoDO user;
        LoginConfig loginConfig = ApplicationContextUtil.getBean(LoginConfig.class);
        if(loginConfig.getMock() && loginConfig.getMockUserId() != null){
            user = userService.getUser(loginConfig.getMockUserId());
            if(user == null){
                gotoLogin(request, response);
                return;
            }
        }
        else {
            String userToken = CookieUtil.getCookieValue(request, Constant.USER_TOKEN_COOKIE_NAME);
            if (StringUtils.isEmpty(userToken)) {
                gotoLogin(request, response);
                LOGGER.info("user token 没有找到");
                return;
            }
            Integer userId = UserTokenManager.getUserId(userToken);
            if (userId == null) {
                gotoLogin(request, response);
                LOGGER.info("user id 没有找到");
                return;
            }

            user = userService.getUser(userId);
            if (user == null) {
                gotoLogin(request, response);
                LOGGER.info("user 信息没有找到");
                return;
            }
        }

        UserHolder.setUser(user);
        try{
            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {
            UserHolder.cleanUser();
        }
    }

    private void gotoLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        CookieUtil.addCookie(response, Constant.USER_TOKEN_COOKIE_NAME, null);
        if(StringUtils.isEmpty(uri) || !uri.endsWith(".json")){
            response.sendRedirect(LOGIN_URL + request.getRequestURL());
        }
        else{
            response.setStatus(401);
        }
    }
}
