package com.gzmu.tcsys.service;

import com.gzmu.tcsys.bean.TcsysCatalog1;
import com.gzmu.tcsys.bean.TcsysCatalog2;
import com.gzmu.tcsys.bean.TcsysCatalog3;
import com.gzmu.tcsys.commmon.ResultData;
import com.gzmu.tcsys.vo.TcsysCatalogGradeVO;
import com.gzmu.tcsys.vo.TcsysCatalogVO;

import java.util.List;

/**
 * @author weicaiwang
 */
public interface CatalogService {

    /**
     * 获取一级目录
     *
     * @return
     */
    List<TcsysCatalog1> getCatalog1();

    /**
     * 获取二级目录
     *
     * @param catalog1Id
     * @return
     */
    List<TcsysCatalog2> getCatalog2(String catalog1Id);

    /**
     * 获取三级目录
     *
     * @param catalog2Id
     * @return
     */
    List<TcsysCatalog3> getCatalog3(String catalog2Id);

    /**
     * 根据type查询对应等级以上的所有目录信息 如type=2 则查找一级和二级目录
     *
     * @param type
     * @return
     */
    List<TcsysCatalogVO> getCatalog(String type);

    /**
     * 根据输入参数获取分页目录信息
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultData getCatalog(String type, Integer pageNum, Integer pageSize );

    /**
     * 保存三级信息
     *
     * @param tcsysCatalog
     * @return
     */
    Boolean saveCatalog3Info(TcsysCatalog3 tcsysCatalog);

    /**
     * 保存二级目录信息
     *
     * @param tcsysCatalog
     * @return
     */
    Boolean saveCatalog2Info(TcsysCatalog2 tcsysCatalog);

    /**
     * 保存一级目录信息
     *
     * @param tcsysCatalog
     * @return
     */
    Boolean saveCatalog1Info(TcsysCatalog1 tcsysCatalog);

    /**
     * 根据主键和等级获取level
     *
     * @param id
     * @param catalogLevel
     * @return
     */
    TcsysCatalogGradeVO getCatalogByIdAndLevel(String id, String catalogLevel);

    /**
     * 更新目录信息
     * @param catalogDelete
     * @param catalogName
     * @param catalogLevel
     * @param id
     * @return
     */
    Boolean updateCatalog(String catalogDelete, String catalogName, String catalogLevel, String id);

    /**
     * 根据id和catalogLevel删除目录信息
     * @param id
     * @param catalogLevel
     * @return
     */
    Boolean deleteCatalog(Integer id, Integer catalogLevel);
}
