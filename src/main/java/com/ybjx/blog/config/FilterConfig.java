package com.ybjx.blog.config;

import com.ybjx.blog.filter.PageFilter;
import com.ybjx.blog.filter.UserFilter;
import com.ybjx.blog.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * filter配置
 * @author ybjx
 * @date 2019/5/2 0:01
 */
@Component
public class FilterConfig {

    /**
     * 配置用户Filter
     * @param loginConfig 登录相关的配置
     * @param userService 用户服务
     * @return 用户filter
     */
    @Bean
    public FilterRegistrationBean getUserFilter(LoginConfig loginConfig, UserService userService){
        FilterRegistrationBean<UserFilter> bean = new FilterRegistrationBean<>();
        UserFilter filter = new UserFilter();
        filter.setUserService(userService);
        bean.setFilter(filter);
        bean.setOrder(1);
        List<String> list = new ArrayList<>();
        list.add("/api/manage/*");
        list.add("/management/*");
        bean.setUrlPatterns(list);

        Map<String, String> map = new HashMap<>(4);
        map.put("ignore-url", loginConfig.getIgnoreUrl());
        bean.setInitParameters(map);
        return bean;
    }

    /**
     * 配置管理端的首页的html跳转filter
     * @return filter
     */
    @Bean
    public FilterRegistrationBean getPageFilter(FileConfig fileConfig){
        FilterRegistrationBean<PageFilter> bean = new FilterRegistrationBean<>();
        PageFilter filter = new PageFilter();
        bean.setFilter(filter);
        bean.setOrder(2);
        List<String> list = new ArrayList<>();
        list.add("/management/*");
        list.add("/login");
        bean.setUrlPatterns(list);
        Map<String, String> map = new HashMap<>(4);
        map.put("management-index-path", fileConfig.getIndex());
        bean.setInitParameters(map);
        return bean;
    }
}
