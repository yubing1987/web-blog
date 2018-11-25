package com.ybjx.blog.store;

/**
 * 文章存储服务接口
 * create by YuBing at 2018/11/25
 */
public interface IFileStore {
    /**
     * 保存文章内容
     * @param articleUuid 文章ID
     * @param content 文章内容
     */
    void save(String articleUuid, String content);

    /**
     * 读取文章内容
     * @param articleUuid 文章ID
     * @return 文章内容
     */
    String read(String articleUuid);
}
