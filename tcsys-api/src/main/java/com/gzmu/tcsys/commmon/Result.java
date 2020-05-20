package com.gzmu.tcsys.commmon;

/**
 * 定义统一的返回格式
 *
 * @author weicaiwang
 */
public interface Result {

    /**
     * 状态
     *
     * @return
     */
    int getCode();

    /**
     * 该状态对应的消息
     *
     * @return
     */
    String getMsg();


}
