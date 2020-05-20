package com.gzmu.tcsys.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 商品属性表
 *
 * @author weicaiwang
 */
public class TcsysAttrInfo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private String id;
    @Column
    private String productAttrName;
    @Column
    private String catalog3Id;
    @Column
    private String isEnabled;
    @Transient
    List<TcsysAttrValue> tcsysAttrValueList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductAttrName() {
        return productAttrName;
    }

    public void setProductAttrName(String productAttrName) {
        this.productAttrName = productAttrName;
    }

    public String getCatalog3Id() {
        return catalog3Id;
    }

    public void setCatalog3Id(String catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public List<TcsysAttrValue> getTcsysAttrValueList() {
        return tcsysAttrValueList;
    }

    public void setTcsysAttrValueList(List<TcsysAttrValue> tcsysAttrValueList) {
        this.tcsysAttrValueList = tcsysAttrValueList;
    }
}
