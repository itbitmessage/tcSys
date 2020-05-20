package com.gzmu.tcsys.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 三级目录
 *
 * @author weicaiwang
 */
public class TcsysCatalog3 implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String catalogName;

    @Column
    private String catalog2Id;

    @Column
    private String catalogDelete;

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

    public String getCatalog2Id() {
        return catalog2Id;
    }

    public void setCatalog2Id(String catalog2Id) {
        this.catalog2Id = catalog2Id;
    }
}
