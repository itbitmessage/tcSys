package com.gzmu.tcsys.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weicaiwang
 */
public class TcsysCatalogVO implements Serializable {

    private String id;

    private String catalogName;

    private String catalogId;

    private Integer catalogLevel;

    private String catalogDelete;

    private ArrayList<TcsysCatalogVO> children;

    public ArrayList<TcsysCatalogVO> getChildren() {
        return children;
    }

    public String getCatalogDelete() {
        return catalogDelete;
    }

    public void setCatalogDelete(String catalogDelete) {
        this.catalogDelete = catalogDelete;
    }

    public void setChildren(ArrayList<TcsysCatalogVO> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public Integer getCatalogLevel() {
        return catalogLevel;
    }

    public void setCatalogLevel(Integer catalogLevel) {
        this.catalogLevel = catalogLevel;
    }
}
