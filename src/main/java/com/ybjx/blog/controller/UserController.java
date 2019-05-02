package com.ybjx.blog.controller;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.common.result.PojoResult;
import com.ybjx.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @return 登录结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public PojoResult<Boolean> login(@RequestParam(name = "name") String name,
                                     @RequestParam(name = "password") String password){
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
            throw new BlogException(ErrorCode.PARAMETER_ERROR, "账号密码不能为空");
        }
        userService.login(name, password);
        return new PojoResult<>(true);
    }
}
