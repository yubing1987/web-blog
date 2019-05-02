package com.ybjx.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 登录相关的配置
 * @author ybjx
 * @date 2019/5/2 0:06
 */
@Configuration
@ConfigurationProperties(prefix = "blog.login")
public class LoginConfig {

    /**
     * 不校验登录信息的url地址
     */
    private String ignoreUrl = "";

    /**
     * 密码加密KEY
     */
    private String key = "";

    public String getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(String ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LoginConfig{" +
                "ignoreUrl='" + ignoreUrl + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
