package com.ybjx.blog.common;

/**
 * 应用自定义异常信息
 * create by YuBing at 2018/11/20
 */
public class BlogException extends RuntimeException {
    /**
     * 错误编码
     */
    private String code;

    /**
     * 指定错误编码
     * @param errorCode 错误编码
     */
    public BlogException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    /**
     * 指定错误编码和错误信息
     * @param errorCode 错误编码
     * @param msg 错误信息
     */
    public BlogException(ErrorCode errorCode, String msg){
        super(msg);
        this.code = errorCode.getCode();
    }

    /**
     * 指定错误编码和父异常
     * @param errorCode 错误编码
     * @param e 父异常
     */
    public BlogException(ErrorCode errorCode, Exception e){
        super(errorCode.getMsg(), e);
        this.code = errorCode.getCode();
    }

    /**
     * 指定错误编码、错误信息和父异常
     * @param errorCode 错误编码
     * @param msg 错误信息
     * @param e 父异常
     */
    public BlogException(ErrorCode errorCode, String msg, Exception e){
        super(msg, e);
        this.code = errorCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
