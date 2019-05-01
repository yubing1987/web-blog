package com.ybjx.blog.config;

import com.ybjx.blog.filter.UserFilter;
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
     * @return 用户filter
     */
    @Bean
    public FilterRegistrationBean getUserFilter(LoginConfig loginConfig){
        FilterRegistrationBean<UserFilter> bean = new FilterRegistrationBean<>();
        UserFilter filter = new UserFilter();
        bean.setFilter(filter);
        bean.setOrder(1);
        List<String> list = new ArrayList<>();
        list.add("/*");
        bean.setUrlPatterns(list);

        Map<String, String> map = new HashMap<>(4);
        map.put("ignore-url", loginConfig.getIgnoreUrl());
        bean.setInitParameters(map);
        return bean;
    }
}
