package com.ybjx.blog.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * HTTP错误请求处理
 * create by YuBing at 2019/2/15
 */
@Configuration
public class HttpErrorConfig implements ErrorPageRegistrar {

    /**
     * 注册错误页面
     * @param registry 错误页面注册器
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages=new ErrorPage[2];
        //　页面没有找到
        errorPages[0]=new ErrorPage(HttpStatus.NOT_FOUND,"/error");
        // 服务异常错误
        errorPages[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error");
        registry.addErrorPages(errorPages);
    }
}
