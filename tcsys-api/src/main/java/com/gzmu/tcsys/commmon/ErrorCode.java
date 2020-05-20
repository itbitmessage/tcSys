package com.gzmu.tcsys.commmon;

import java.io.Serializable;

/**
 * 错误码
 *
 * @author weicaiwang
 */
public enum ErrorCode implements Serializable {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAIL(210, "操作失败"),
    /**
     * 登录错误
     */
    LOGIN_ERROR(211, "用户名或密码错误!");

    /**
     * 状态码
     */
    private int code;
    /**
     * 状态码对应的描述
     */
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
