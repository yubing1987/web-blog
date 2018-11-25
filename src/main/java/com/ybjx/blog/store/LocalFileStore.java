package com.ybjx.blog.store;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import com.ybjx.blog.config.ArticleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 把文章内容保存在本地的服务
 * create by YuBing at 2018/11/25
 */
@Service
@ConditionalOnProperty(name = "blog.article.save-at-local", havingValue = "true")
public class LocalFileStore implements IFileStore {
    /**
     * 文章缓存
     */
    private Map<String, String> cache = new ConcurrentHashMap<>();

    /**
     * 文章相关的配置
     */
    private final ArticleConfig articleConfig;

    @Autowired
    public LocalFileStore(ArticleConfig articleConfig) {
        this.articleConfig = articleConfig;
    }

    /**
     * 保存文章内容
     * @param articleUuid 文章ID
     * @param content 文章内容
     */
    @Override
    public void save(String articleUuid, String content) {
        File dir = new File(articleConfig.getFileBasePath());
        if(!dir.exists() || !dir.isDirectory()){
            throw new BlogException(ErrorCode.SYSTEM_ERROR, "文章内容保存出错");
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(articleConfig.getFileBasePath() + '/' + articleUuid + ".md");
            outputStream.write(content.getBytes("UTF-8"));
            cache.put(articleUuid, content);
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.SYSTEM_ERROR, "文章内容保存出错", e);
        }
    }

    /**
     * 读取文章内容
     * @param articleUuid 文章ID
     * @return 文章内容
     */
    @Override
    public String read(String articleUuid) {
        String content = cache.get(articleUuid);
        if(content != null){
            return content;
        }
        try {
            FileReader fileReader = new FileReader(articleConfig.getFileBasePath() + "/" + articleUuid + ".md");

            char[] buffer = new char[1024];
            StringBuilder builder = new StringBuilder();
            while (true) {
                int count = fileReader.read(buffer);
                if (count <= 0) {
                    break;
                }
                builder.append(buffer, 0, count);
            }
            cache.put(articleUuid, builder.toString());
            return builder.toString();
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.SYSTEM_ERROR, "读取文章内容出错", e);
        }
    }
}
