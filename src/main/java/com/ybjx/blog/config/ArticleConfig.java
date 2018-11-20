package com.ybjx.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文章相关的配置
 * create by YuBing at 2018/11/20
 */
@Configuration
@ConfigurationProperties(prefix = "blog.article")
public class ArticleConfig {
    /**
     * 文章存放位置
     */
    private String fileBasePath;

    /**
     * 是否存放在本地
     */
    private Boolean saveAtLocal = true;

    public String getFileBasePath() {
        return fileBasePath;
    }

    public void setFileBasePath(String fileBasePath) {
        this.fileBasePath = fileBasePath;
    }

    public Boolean getSaveAtLocal() {
        return saveAtLocal;
    }

    public void setSaveAtLocal(Boolean saveAtLocal) {
        this.saveAtLocal = saveAtLocal;
    }

    @Override
    public String toString() {
        return "ArticleConfig{" +
                "fileBasePath='" + fileBasePath + '\'' +
                ", saveAtLocal=" + saveAtLocal +
                '}';
    }
}
