package com.ybjx.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 文件配置
 * create by YuBing at 2019/2/17
 */
@Configuration
@ConfigurationProperties(prefix = "blog.file")
public class FileConfig {
    /**
     * 文件存放路径
     */
    private String location;

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

    @Override
    public String toString() {
        return "FileConfig{" +
                "location='" + location + '\'' +
                '}';
    }
}
