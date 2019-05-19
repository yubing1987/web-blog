package com.ybjx.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 文件配置
 * @author ybjx
 * @date 2019/5/1 23:12
 */
@Configuration
@ConfigurationProperties(prefix = "blog.file")
public class FileConfig {
    /**
     * 文件存放路径
     */
    private String location;

    /**
     * 首页地址
     */
    private String index;

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(50 * 1024 * 1024);
        resolver.setMaxUploadSize(50 * 1024 * 1024);
        return resolver;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "FileConfig{" +
                "location='" + location + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
