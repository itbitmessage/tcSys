package com.gzmu.tcsys.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 一级目录
 *
 * @author weicaiwang
 */
public class TcsysCatalog1 implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String catalogName;

    @Column
    private String catalogDelete;

    @Transient
    private List<TcsysCatalog2> catalog2List;


    public String getCatalogDelete() {
        return catalogDelete;
    }

    public void setCatalogDelete(String catalogDelete) {
        this.catalogDelete = catalogDelete;
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

    public List<TcsysCatalog2> getCatalog2List() {
        return catalog2List;
    }

    public void setCatalog2List(List<TcsysCatalog2> catalog2List) {
        this.catalog2List = catalog2List;
    }
}
