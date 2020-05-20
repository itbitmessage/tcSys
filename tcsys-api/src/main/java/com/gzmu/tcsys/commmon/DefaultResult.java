package com.gzmu.tcsys.commmon;

import java.io.Serializable;

/**
 * 默认返回结果
 *
 * @author weicaiwang
 */
public class DefaultResult implements Result, Serializable {

    private int code;
    private String msg;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    /**
     * 状态
     *
     * @return
     */
    public int getCode() {
        return this.code;
    }


    /**
     * 该状态对应的消息
     *
     * @return
     */
    public String getMsg() {
        return this.msg;
    }


    public DefaultResult() {
    }

    public DefaultResult(ErrorCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }


    /**
     * 成功方法
     *
     * @return
     */
    public static Result success() {
        return new DefaultResult( ErrorCode.SUCCESS );
    }

    /**
     * 失败方法
     *
     * @return
     */
    public static Result fail() {
        return new DefaultResult( ErrorCode.FAIL );
    }
}
