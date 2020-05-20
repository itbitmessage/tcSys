package com.gzmu.tcsys.service;

import com.gzmu.tcsys.bean.TcsysAttrInfo;

import java.util.List;

/**
 * @author weicaiwang
 */
public interface TcsysAttrService {

    /**
     * 根据catalog3Id获取属性信息
     *
     * @param catalog3Id
     * @return
     */
    List<TcsysAttrInfo> getTcsysAttrInfo(String catalog3Id);

    /**
     * 根据属性id获取属性信息
     * @param id
     * @return
     */
    TcsysAttrInfo getAttrInfoById(String id);

    /**
     * 根据id修改属性信息名
     * @param id
     * @param productAttrName
     * @return
     */
    Boolean editAttrInfoName(String id, String productAttrName);

    /**
     * 根据id删除属性信息
     * @param id
     * @return
     */
    Boolean deleteAttrInfoById(String id);

    /**
     * 添加属性信息
     * @param tcsysAttrInfo
     * @return
     */
    Boolean saveAttrInfo(TcsysAttrInfo tcsysAttrInfo);

    /**
     * 添加属性值信息
     * @param id
     * @param tcsysAttrValueList
     * @return
     */
    Boolean saveAttrValue(String id, String tcsysAttrValueList);
}
