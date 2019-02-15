package com.ybjx.blog.common.result;

import com.ybjx.blog.common.ErrorCode;

import java.io.Serializable;

/**
 * 基础的返回结果
 * Created by YuBing on 2018/11/19.
 */
public abstract class BaseResult implements Serializable {
    /**
     * 结果是否成功
     */
    private Boolean success = true;

    /**
     * 结果编码
     */
    private String code = ErrorCode.NONE_ERROR.getCode();

    /**
     * 错误信息
     */
    private String message = ErrorCode.NONE_ERROR.getMsg();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
