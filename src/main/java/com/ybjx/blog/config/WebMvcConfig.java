package com.ybjx.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 设置url后缀匹配
 * @author ybjx
 * @date 2019/5/11 23:55
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * -设置url后缀模式匹配规则
     * -该设置匹配所有的后缀
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(true)
                .setUseTrailingSlashMatch(true);
    }
}
