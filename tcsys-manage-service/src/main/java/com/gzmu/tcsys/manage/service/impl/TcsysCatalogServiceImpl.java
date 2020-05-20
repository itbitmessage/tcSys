package com.gzmu.tcsys.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzmu.tcsys.bean.TcsysCatalog1;
import com.gzmu.tcsys.bean.TcsysCatalog2;
import com.gzmu.tcsys.bean.TcsysCatalog3;
import com.gzmu.tcsys.commmon.DefaultResultData;
import com.gzmu.tcsys.commmon.ResultData;
import com.gzmu.tcsys.manage.mapper.TcsysCatalog1Mapper;
import com.gzmu.tcsys.manage.mapper.TcsysCatalog2Mapper;
import com.gzmu.tcsys.manage.mapper.TcsysCatalog3Mapper;
import com.gzmu.tcsys.service.CatalogService;
import com.gzmu.tcsys.vo.TcsysCatalogGradeVO;
import com.gzmu.tcsys.vo.TcsysCatalogVO;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录实现
 *
 * @author weicaiwang
 */
@Service
public class TcsysCatalogServiceImpl implements CatalogService {

    @Autowired
    TcsysCatalog1Mapper tcsysCatalog1Mapper;

    @Autowired
    TcsysCatalog2Mapper tcsysCatalog2Mapper;

    @Autowired
    TcsysCatalog3Mapper tcsysCatalog3Mapper;

    /**
     * 获取一级目录
     *
     * @return
     */
    @Override
    public List<TcsysCatalog1> getCatalog1() {
        return tcsysCatalog1Mapper.selectAll();
    }

    /**
     * 获取二级目录
     *
     * @param catalog1Id
     * @return
     */
    @Override
    public List<TcsysCatalog2> getCatalog2(String catalog1Id) {
        TcsysCatalog2 tcsysCatalog2 = new TcsysCatalog2();
        tcsysCatalog2.setCatalog1Id( catalog1Id );
        List<TcsysCatalog2> tcsysCatalog2List = tcsysCatalog2Mapper.select( tcsysCatalog2 );
        return tcsysCatalog2List;
    }

    /**
     * 获取三级目录
     *
     * @param catalog2Id
     * @return
     */
    @Override
    public List<TcsysCatalog3> getCatalog3(String catalog2Id) {
        TcsysCatalog3 tcsysCatalog3 = new TcsysCatalog3();
        tcsysCatalog3.setCatalog2Id( catalog2Id );
        List<TcsysCatalog3> tcsysCatalog3List = tcsysCatalog3Mapper.select( tcsysCatalog3 );
        return tcsysCatalog3List;
    }

    /**
     * 根据输入参数获取分页目录信息
     *
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultData getCatalog(String type, Integer pageNum, Integer pageSize) {
        //设置分页信息，包括当前页数和每页显示的总计数
        PageHelper.startPage( pageNum, pageSize );
        List<TcsysCatalog1> catalog1 = tcsysCatalog1Mapper.selectAll();
        PageInfo<TcsysCatalog1> pageInfo = new PageInfo<>( catalog1 );
        long total = pageInfo.getTotal();
        List<TcsysCatalogVO> tcsysCatalogVOS = new ArrayList<>();
        for (int i = 0; i < catalog1.size(); i++) {
            //将查询的一级目录添加到vo中
            this.addCatalogToTcsysCatalogVOs( tcsysCatalogVOS
                    , catalog1.get( i ).getId()
                    , catalog1.get( i ).getCatalogName()
                    , catalog1.get( i ).getId()
                    , catalog1.get( i ).getCatalogDelete()
                    , 1 );
            QueryCatalog2( catalog1, tcsysCatalogVOS, i, type );
        }
        return DefaultResultData.data( tcsysCatalogVOS, total );
    }

    /**
     * 根据type查询对应等级以上的所有目录 如type=2 则查找一级和二级目录
     *
     * @param type
     * @return
     */
    @Override
    public List<TcsysCatalogVO> getCatalog(String type) {
        List<TcsysCatalog1> catalog1 = tcsysCatalog1Mapper.selectAll();
        List<TcsysCatalogVO> tcsysCatalogVOS = new ArrayList<>();
        for (int i = 0; i < catalog1.size(); i++) {
            //将查询的一级目录添加到vo中
            this.addCatalogToTcsysCatalogVOs( tcsysCatalogVOS
                    , catalog1.get( i ).getId()
                    , catalog1.get( i ).getCatalogName()
                    , catalog1.get( i ).getId()
                    , catalog1.get( i ).getCatalogDelete()
                    , 1 );
            QueryCatalog2( catalog1, tcsysCatalogVOS, i, type );
        }
        return tcsysCatalogVOS;
    }

    /**
     * 保存一级目录信息
     *
     * @param tcsysCatalog
     * @return
     */
    @Override
    public Boolean saveCatalog1Info(TcsysCatalog1 tcsysCatalog) {
        int res = tcsysCatalog1Mapper.insertSelective( tcsysCatalog );
        return res > 0;
    }

    /**
     * 保存二级目录信息
     *
     * @param tcsysCatalog
     * @return
     */
    @Override
    public Boolean saveCatalog2Info(TcsysCatalog2 tcsysCatalog) {
        int res = tcsysCatalog2Mapper.insertSelective( tcsysCatalog );
        return res > 0;
    }

    /**
     * 保存三级信息
     *
     * @param tcsysCatalog
     * @return
     */
    @Override
    public Boolean saveCatalog3Info(TcsysCatalog3 tcsysCatalog) {
        int insert = tcsysCatalog3Mapper.insertSelective( tcsysCatalog );
        return insert > 0;
    }


    /**
     * 查询二级目录
     *
     * @param catalog1
     * @param tcsysCatalogVOS
     * @param i
     * @param type            目录类型 1级目录 2级目录 3级目录
     */
    private void QueryCatalog2(List<TcsysCatalog1> catalog1, List<TcsysCatalogVO> tcsysCatalogVOS, int i, String type) {
        //查询所有的二级目录
        ArrayList<TcsysCatalogVO> tcsysCatalogVOS2 = new ArrayList<>();
        String catalog2Id = catalog1.get( i ).getId();
        List<TcsysCatalog2> catalog2 = this.getCatalog2( catalog2Id );
        for (int j = 0; j < catalog2.size(); j++) {
            //将查询的二级目录添tcsysCatalogVOS2
            this.addCatalogToTcsysCatalogVOs( tcsysCatalogVOS2
                    , catalog2.get( j ).getId()
                    , catalog2.get( j ).getCatalogName()
                    , catalog2.get( j ).getCatalog1Id()
                    , catalog2.get( j ).getCatalogDelete()
                    , 2 );
            //判断要查询的目录类型，如果type等于3,查询三级目录
            if ("3".equals( type )) {
                QueryCatalog3( tcsysCatalogVOS2, catalog2, j );
            }
        }
        //将查询的所有二级菜单添加到   tcsysCatalogVOS
        tcsysCatalogVOS.get( i ).setChildren( tcsysCatalogVOS2 );
    }

    /**
     * 查询三级目录
     *
     * @param tcsysCatalogVOS2
     * @param catalog2
     * @param j
     */
    private void QueryCatalog3(ArrayList<TcsysCatalogVO> tcsysCatalogVOS2, List<TcsysCatalog2> catalog2, int j) {
        //查询所有的三级目录
        ArrayList<TcsysCatalogVO> tcsysCatalogVOS3 = new ArrayList<>();

        String catalog3Id = catalog2.get( j ).getId();
        List<TcsysCatalog3> catalog3 = this.getCatalog3( catalog3Id );

        for (int k = 0; k < catalog3.size(); k++) {
            this.addCatalogToTcsysCatalogVOs( tcsysCatalogVOS3
                    , catalog3.get( k ).getId()
                    , catalog3.get( k ).getCatalogName()
                    , catalog3.get( k ).getCatalog2Id()
                    , catalog3.get( k ).getCatalogDelete()
                    , 3 );
        }
        //将查询的所有二级菜单添加到   tcsysCatalogVOS2
        tcsysCatalogVOS2.get( j ).setChildren( tcsysCatalogVOS3 );
    }

    /**
     * 将目录信息添加到tcsysCatalogVOs集合中
     *
     * @param tcsysCatalogVOs
     * @param id
     * @param catalogName
     * @param CatalogId
     * @param level
     * @return
     */
    private List<TcsysCatalogVO> addCatalogToTcsysCatalogVOs(List<TcsysCatalogVO> tcsysCatalogVOs, String id, String catalogName, String CatalogId, String CatalogDelete, Integer level) {
        TcsysCatalogVO tcsysCatalogVO = new TcsysCatalogVO();
        tcsysCatalogVO.setId( id );
        tcsysCatalogVO.setCatalogName( catalogName );
        tcsysCatalogVO.setCatalogId( CatalogId );
        tcsysCatalogVO.setCatalogDelete( CatalogDelete );
        tcsysCatalogVO.setCatalogLevel( level );
        tcsysCatalogVOs.add( tcsysCatalogVO );
        return tcsysCatalogVOs;
    }

    /**
     * 根据主键和等级获取level
     *
     * @param id
     * @param catalogLevel
     * @return
     */
    @Override
    public TcsysCatalogGradeVO getCatalogByIdAndLevel(String id, String catalogLevel) {

        TcsysCatalogGradeVO tcsysCatalogGradeVO = new TcsysCatalogGradeVO();
        if ("1".equals( catalogLevel )) {
            TcsysCatalog1 tcsysCatalog1 = tcsysCatalog1Mapper.selectByPrimaryKey( id );
            tcsysCatalogGradeVO.setCalatlog( tcsysCatalog1 );
            tcsysCatalogGradeVO.setGrade( 1 );
        } else if ("2".equals( catalogLevel )) {

            TcsysCatalog2 tcsysCatalog2 = tcsysCatalog2Mapper.selectByPrimaryKey( id );
            tcsysCatalogGradeVO.setCalatlog( tcsysCatalog2 );
            tcsysCatalogGradeVO.setGrade( 2 );

        } else if ("3".equals( catalogLevel )) {
            TcsysCatalog3 tcsysCatalog3 = tcsysCatalog3Mapper.selectByPrimaryKey( id );
            tcsysCatalogGradeVO.setCalatlog( tcsysCatalog3 );
            tcsysCatalogGradeVO.setGrade( 3 );
        }

        return tcsysCatalogGradeVO;
    }

    /**
     * 更新目录信息
     *
     * @param catalogDelete
     * @param catalogName
     * @param catalogLevel
     * @param id
     * @return
     */
    @Override
    public Boolean updateCatalog(String catalogDelete, String catalogName, String catalogLevel, String id) {

        int flag = 0;

        if ("1".equals( catalogLevel )) {
            TcsysCatalog1 tcsysCatalog1 = new TcsysCatalog1();
            tcsysCatalog1.setCatalogDelete( catalogDelete );
            tcsysCatalog1.setId( id );
            tcsysCatalog1.setCatalogName( catalogName );

            flag = tcsysCatalog1Mapper.updateByPrimaryKeySelective( tcsysCatalog1 );


        } else if ("2".equals( catalogLevel )) {
            TcsysCatalog2 tcsysCatalog2 = new TcsysCatalog2();
            tcsysCatalog2.setCatalogDelete( catalogDelete );
            tcsysCatalog2.setId( id );
            tcsysCatalog2.setCatalogName( catalogName );

            flag = tcsysCatalog2Mapper.updateByPrimaryKeySelective( tcsysCatalog2 );

        } else if ("3".equals( catalogLevel )) {
            TcsysCatalog3 tcsysCatalog3 = new TcsysCatalog3();
            tcsysCatalog3.setCatalogDelete( catalogDelete );
            tcsysCatalog3.setId( id );
            tcsysCatalog3.setCatalogName( catalogName );

            flag = tcsysCatalog3Mapper.updateByPrimaryKeySelective( tcsysCatalog3 );
        }

        return flag > 0;
    }

    /**
     * 根据id和catalogLevel删除目录信息
     *
     * @param id
     * @param catalogLevel
     * @return
     */
    @Override
    public Boolean deleteCatalog(Integer id, Integer catalogLevel) {

        String strId = id + "";

        //判断是第几级目录
        if (3 == catalogLevel) {
            //如果是第三级 就直接删除
            int i = tcsysCatalog3Mapper.deleteByPrimaryKey( strId );
            return i > 0;

        } else if (2 == catalogLevel) {
            isContainChildrenCatalog( strId );
            int i = tcsysCatalog2Mapper.deleteByPrimaryKey( strId );
            return i > 0;

        } else {

            Example example = new Example( TcsysCatalog2.class );
            example.createCriteria().andEqualTo( "catalog1Id", strId );
            List<TcsysCatalog2> tcsysCatalog2s = tcsysCatalog2Mapper.selectByExample( example );

            //判断是否含有二目录
            if (!tcsysCatalog2s.isEmpty()) {
                for (int i = 0; i < tcsysCatalog2s.size(); i++) {
                    //判断二级目录下是否还含有三级目录
                    isContainChildrenCatalog( tcsysCatalog2s.get( i ).getId() );
                    int del = tcsysCatalog3Mapper.deleteByPrimaryKey( tcsysCatalog2s.get( i ).getId() );
                    return del > 0;
                }
            }
            int i = tcsysCatalog1Mapper.deleteByPrimaryKey( strId );
            return i > 0;
        }
    }

    /**
     * 判断二级目录下是否还含有子目录
     * 如果有就删除
     *
     * @param id
     */
    private void isContainChildrenCatalog(String id) {
        //根据id查询三级目，判断是否存在子目录
        Example example = new Example( TcsysCatalog3.class );
        example.createCriteria().andEqualTo( "catalog2Id", id );
        List<TcsysCatalog3> tcsysCatalog3s = tcsysCatalog3Mapper.selectByExample( example );
        if (!tcsysCatalog3s.isEmpty()) {
            for (int i = 0; i < tcsysCatalog3s.size(); i++) {
                tcsysCatalog3Mapper.deleteByPrimaryKey( tcsysCatalog3s.get( i ).getId() );
            }
        }
    }
}

