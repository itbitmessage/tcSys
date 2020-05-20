package com.gzmu.tcsys.commmon;

/**
 * 统一返回格式,带数据
 *
 * @author weicaiwang
 */
public interface ResultData extends Result {

    /**
     * 获取数据
     * @return
     */
    Object getData();

}
