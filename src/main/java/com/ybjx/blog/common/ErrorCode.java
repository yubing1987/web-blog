package com.ybjx.blog.common;

/**
 * 错误编码
 * @author ybjx
 * @date 2019/5/1 21:48
 */
public enum ErrorCode {
    /**
     * 错误信息
     */
    NONE_ERROR("200", ""),
    SYSTEM_ERROR("10000", "系统错误"),
    OBJECT_EXIST("10001", "数据已经存在"),
    PARAMETER_ERROR("10002", "参数错误"),
    DATABASE_INSERT("10003", "数据库插入错误"),
    OBJECT_NOT_FOUND("10004", "数据没有找到"),
    OBJECT_UPDATE_ERROR("10005", "数据更新出错"),
    OBJECT_STATUS_ERROR("10006", "状态错误"),
    FILE_SAVE_ERROR("10007", "保存文件出错"),
    FILE_READ_ERROR("10008", "读取文件出错"),
    OBJECT_DELETE_ERROR("10009", "删除数据出错"),
    PERMISSION_DENIED("10010", "无权限"),
    STATUS_ERROR("100011", "状态错误");

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
