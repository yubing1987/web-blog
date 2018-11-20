package com.ybjx.blog.common;

/**
 * 错误编码
 * create by YuBing at 2018/11/20
 */
public enum ErrorCode {
    SYSTEM_ERROR("10000", "系统错误"),
    OBJECT_EXIST("10001", "数据已经存在"),
    PARAMETER_ERROR("10002", "参数错误");

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
