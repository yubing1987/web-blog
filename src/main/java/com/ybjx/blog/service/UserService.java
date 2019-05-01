package com.ybjx.blog.service;

import com.ybjx.blog.dao.UserInfoMapper;
import com.ybjx.blog.entity.UserInfoDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户相关的服务
 * @author ybjx
 * @date 2019/5/1 23:29
 */
@Service
public class UserService {

    /**
     * 日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * 用户数据库操作
     */
    private final UserInfoMapper userMapper;

    /**
     * 构造方法
     * @param userMapper -
     */
    @Autowired
    public UserService(UserInfoMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 用户用户ID查询用户ixnx
     * @param id 用户ID
     * @return 查找到的用户信息
     */
    public UserInfoDO getUser(Integer id){
        try {
            return userMapper.selectByPrimaryKey(id);
        }
        catch (Exception e){
            LOGGER.error("通过用户ID查询用户信息出错", e);
            return null;
        }

    }

}
