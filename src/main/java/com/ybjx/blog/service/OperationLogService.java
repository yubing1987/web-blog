package com.ybjx.blog.service;

import com.ybjx.blog.common.OperationTyp;
import com.ybjx.blog.dao.OperationLogMapper;
import com.ybjx.blog.entity.OperationLogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 日志相关的功能
 * @author ybjx
 * @date 2019/5/3 9:59
 */
@Service
public class OperationLogService {

    /**
     * 本地日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationLogService.class);

    /**
     * 日志数据库操作
     */
    private final OperationLogMapper logMapper;

    /**
     * 构造方法
     * @param logMapper -
     */
    @Autowired
    public OperationLogService(OperationLogMapper logMapper) {
        this.logMapper = logMapper;
    }

    /**
     * 记录操作日志
     * @param message 日志信息
     * @param targetId 操作对象
     * @param type 操作对象类型
     * @param userId 操作者ID
     */
    public void addLog(String message, int targetId, OperationTyp type, int userId){
        OperationLogDO log = new OperationLogDO();
        log.setCreateDate(new Date());
        log.setIsDeleted(false);
        log.setMessage(message);
        log.setTargetId(targetId);
        log.setTargetType(type.name());
        log.setOperator(userId);
        try{
            logMapper.insert(log);
        }
        catch (Exception e){
            LOGGER.error("记录操作日志出错：" + log.toString(), e);
        }
    }
}
