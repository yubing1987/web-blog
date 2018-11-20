package com.ybjx.blog.common.result;

/**
 * 基础的返回结果
 * @param <T> 返回值对象
 * Created by YuBing on 2018/11/19.
 */
public abstract class BaseResult<T> {
    /**
     * 结果是否成功
     */
    private Boolean success;

    /**
     * 结果编码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回的内容
     */
    private T content;

    public BaseResult(T content) {
        this.content = content;
        this.code = "200";
        this.success = true;
        this.message = "";
    }

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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
