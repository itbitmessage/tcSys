package com.gzmu.tcsys.commmon;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author weicaiwang
 */
public class DefaultResultData implements ResultData, Serializable {

    private int code;
    private String msg;
    private Object data;
    private Long total;

    public DefaultResultData() {
    }

    public DefaultResultData(ErrorCode code, Object data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

    public DefaultResultData(ErrorCode code, Object data,Long total) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
        this.total = total;
    }

    public static ResultData data(Object data) {
        return new DefaultResultData( ErrorCode.SUCCESS, data );
    }
    public static ResultData data(Object data,Long total) {
        return new DefaultResultData( ErrorCode.SUCCESS, data,total );
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 获取数据
     *
     * @return
     */
    @Override
    public Object getData() {
        return this.data;
    }

    /**
     * 状态
     *
     * @return
     */
    @Override
    public int getCode() {
        return this.code;
    }

    /**
     * 该状态对应的消息
     *
     * @return
     */
    @Override
    public String getMsg() {
        return this.msg;
    }
}
