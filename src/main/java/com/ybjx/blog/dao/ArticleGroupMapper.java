package com.ybjx.blog.dao;

import com.ybjx.blog.entity.ArticleGroupDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 文章分组数据库操作
 * @author ybjx
 */
public interface ArticleGroupMapper extends Mapper<ArticleGroupDO> {

    /**
     * 查询分组列表
     * @param userId 所有者ID
     * @param type 分组类型
     * @param key 搜索关键字
     * @return 查找到的分组信息
     */
    List<ArticleGroupDO> queryGroupList(@Param("userId") Integer userId,
                                        @Param("type") String type,
                                        @Param("key") String key);
}