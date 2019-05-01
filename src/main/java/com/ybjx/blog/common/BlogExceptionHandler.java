package com.ybjx.blog.common;

import com.ybjx.blog.common.result.PojoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 接口异常统一处理
 * @author ybjx
 * @date 2019/5/1 21:48
 */
@ControllerAdvice
public class BlogExceptionHandler {
    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(BlogExceptionHandler.class);

    /**
     * 异常处理类
     * @param e 异常信息
     * @return 统一格式返回结果，屏蔽内部未知的错误信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public PojoResult<Boolean> defaultErrorHandler(Exception e) {
        PojoResult<Boolean> result = new PojoResult<>();
        result.setSuccess(false);
        if (e instanceof BlogException) {
            // 应用已知的异常信息
            BlogException be = (BlogException) e;
            result.setCode(be.getCode());
            result.setMessage(be.getMessage());
        } else {
            // 未知的异常信息
            result.setCode(ErrorCode.SYSTEM_ERROR.getCode());
            result.setMessage(ErrorCode.SYSTEM_ERROR.getMsg());
        }
        LOGGER.error(result.getMessage(), e);
        return result;
    }
}
