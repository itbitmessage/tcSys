package com.gzmu.tcsys.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gzmu.tcsys.bean.TcsysAttrInfo;
import com.gzmu.tcsys.bean.TcsysAttrValue;
import com.gzmu.tcsys.manage.mapper.TcsysAttrInfoMapper;
import com.gzmu.tcsys.manage.mapper.TcsysAttrValueMapper;
import com.gzmu.tcsys.service.TcsysAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author weicaiwang
 */
@Service
public class TcsysAttrServiceImpl implements TcsysAttrService {

    @Autowired
    TcsysAttrInfoMapper tcsysAttrInfoMapper;

    @Autowired
    TcsysAttrValueMapper tcsysAttrValueMapper;

    /**
     * 根据catalog3Id获取属性信息
     *
     * @param catalog3Id
     * @return
     */
    @Override
    public List<TcsysAttrInfo> getTcsysAttrInfo(String catalog3Id) {
        TcsysAttrInfo tcsysAttrInfo = new TcsysAttrInfo();
        tcsysAttrInfo.setCatalog3Id( catalog3Id );
        List<TcsysAttrInfo> tcsysAttrInfoList = tcsysAttrInfoMapper.select( tcsysAttrInfo );
        for (int i = 0; i < tcsysAttrInfoList.size(); i++) {
            Example example = new Example( TcsysAttrValue.class );
            example.createCriteria().andEqualTo( "attrId", tcsysAttrInfoList.get( i ).getId() );
            List<TcsysAttrValue> tcsysAttrValues = tcsysAttrValueMapper.selectByExample( example );
            tcsysAttrInfoList.get( i ).setTcsysAttrValueList( tcsysAttrValues );
        }
        return tcsysAttrInfoList;
    }

    /**
     * 根据属性id获取属性信息
     *
     * @param id
     * @return
     */
    @Override
    public TcsysAttrInfo getAttrInfoById(String id) {
        TcsysAttrInfo tcsysAttrInfo = tcsysAttrInfoMapper.selectByPrimaryKey( id );
        return tcsysAttrInfo;
    }

    /**
     * 根据id修改属性信息名
     *
     * @param id
     * @param productAttrName
     * @return
     */
    @Override
    public Boolean editAttrInfoName(String id, String productAttrName) {
        TcsysAttrInfo tcsysAttrInfo = new TcsysAttrInfo();
        tcsysAttrInfo.setId( id );
        tcsysAttrInfo.setProductAttrName( productAttrName );
        int i = tcsysAttrInfoMapper.updateByPrimaryKeySelective( tcsysAttrInfo );
        return i > 0;
    }

    /**
     * 根据id删除属性信息
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteAttrInfoById(String id) {
        //查询是否又属性值，如果存在就删除
        Example example = new Example( TcsysAttrValue.class );
        example.createCriteria().andEqualTo( "attrId", id );
        tcsysAttrValueMapper.deleteByExample( example );
        int res = tcsysAttrInfoMapper.deleteByPrimaryKey( id );
        return res > 0;
    }

    /**
     * 添加属性信息
     *
     * @param tcsysAttrInfo
     * @return
     */
    @Override
    public Boolean saveAttrInfo(TcsysAttrInfo tcsysAttrInfo) {
        int i = tcsysAttrInfoMapper.insertSelective( tcsysAttrInfo );
        return i > 0;
    }

    /**
     * 添加属性值信息
     *
     * @param id
     * @param tcsysAttrValueList
     * @return
     */
    @Override
    public Boolean saveAttrValue(String id, String tcsysAttrValueList) {
        String[] strings = tcsysAttrValueList.split( "\"" );
        String[] tcsysAttrValues = strings[3].split( " " );
        //去重
        Set<String> tempSet = new HashSet<>(  );
        for (int i = 0; i < tcsysAttrValues.length; i++) {
            tempSet.add( tcsysAttrValues[i] );
        }
        List<String> temp = new ArrayList<>( tempSet );
        //删除原来所有
        Example example = new Example( TcsysAttrValue.class );
        example.createCriteria().andEqualTo( "attrId" ,id);
        tcsysAttrValueMapper.deleteByExample( example );
        //新增
        for (int i = 0; i < temp.size(); i++) {
            TcsysAttrValue tcsysAttrValue = new TcsysAttrValue();
            tcsysAttrValue.setAttrId( id );
            tcsysAttrValue.setProductValueName( temp.get( i ) );
            int i1 = tcsysAttrValueMapper.insertSelective( tcsysAttrValue );
            if (i1 < 1){
                tcsysAttrValueMapper.deleteByExample( example );
                return false;
            }
        }
        return true;
    }
}
