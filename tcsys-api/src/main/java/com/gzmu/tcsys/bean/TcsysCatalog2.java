package com.gzmu.tcsys.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 二级目录
 *
 * @author weicaiwang
 */
public class TcsysCatalog2 implements Serializable {


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String catalogName;


    @Column
    private String catalogDelete;

    @Column
    private String catalog1Id;

    @Transient
    private List<TcsysCatalog3> catalog3List;

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

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public void setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
    }

    public List<TcsysCatalog3> getCatalog3List() {
        return catalog3List;
    }

    public void setCatalog3List(List<TcsysCatalog3> catalog3List) {
        this.catalog3List = catalog3List;
    }
}
