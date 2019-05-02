package com.ybjx.blog.controller;

import com.ybjx.blog.common.*;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.entity.UserInfoDO;
import com.ybjx.blog.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * login logout接口
 * @author ybjx
 * @date 2019/5/2 12:05
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    /**
     * 用户服务
     */
    private final UserService userService;

    /**
     * 构造方法
     * @param userService -
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录接口
     * @param name 用户名
     * @param password 用户密码
     */
    @RequestMapping(value = "/login")
    public PojoResult<Boolean> login(@RequestParam(name = "name") String name,
                      @RequestParam(name = "password") String password,
                      HttpServletResponse response){
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            throw new BlogException(ErrorCode.PARAMETER_ERROR, "账号密码不能为空");
        }
        String token = userService.login(name, password);
        CookieUtil.addCookie(response, Constant.USER_TOKEN_COOKIE_NAME, token);
        return new PojoResult<>(true);
    }

    /**
     * 退出登录
     * @param request -
     * @param response -
     * @return 退出结果
     */
    @RequestMapping(value = "/logout")
    public PojoResult<Boolean> logout(HttpServletRequest request, HttpServletResponse response){
        String token = CookieUtil.getCookieValue(request, Constant.USER_TOKEN_COOKIE_NAME);
        if(!StringUtils.isEmpty(token)){
            UserTokenManager.removeToken(token);
        }
        CookieUtil.addCookie(response, Constant.USER_TOKEN_COOKIE_NAME, null);
        UserHolder.cleanUser();
        return new PojoResult<>(true);
    }
}
