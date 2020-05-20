package com.gzmu.tcsys.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gzmu.tcsys.bean.TcsysCatalog1;
import com.gzmu.tcsys.bean.TcsysCatalog2;
import com.gzmu.tcsys.bean.TcsysCatalog3;
import com.gzmu.tcsys.commmon.DefaultResult;
import com.gzmu.tcsys.commmon.DefaultResultData;
import com.gzmu.tcsys.commmon.Result;
import com.gzmu.tcsys.commmon.ResultData;
import com.gzmu.tcsys.manage.util.Page;
import com.gzmu.tcsys.vo.TcsysCatalogGradeVO;
import com.gzmu.tcsys.vo.TcsysCatalogVO;
import com.gzmu.tcsys.service.CatalogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 后台目录controller
 *
 * @author weicaiwang
 */

@Controller
//解决跨域问题
@CrossOrigin
public class TcsysCatalogController {

    @Reference
    CatalogService catalogService;

    /**
     * 获取分页目录
     *
     * @param type 目录的类型
     * @return
     */
    @RequestMapping("getCatalog")
    @ResponseBody
    public ResultData getCatalog(String type, Integer pageNum, Integer pageSize) {

        //判断非空
        if (pageNum == null) {
            //设置默认当前页
            pageNum = 1;
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            //设置默认每页显示的条数
            pageSize = 5;
        }
        //查询
        ResultData resultData = catalogService.getCatalog( type, pageNum, pageSize );
        return resultData;
    }

    /**
     * 根据type查询对应等级以上的所有目录信息 如type=2 则查找一级和二级目录
     * @param type
     * @return
     */
    @RequestMapping("getCatalogByType")
    @ResponseBody
    public ResultData getCatalog(String type){
        List<TcsysCatalogVO> catalog = catalogService.getCatalog( type );
        ResultData data = DefaultResultData.data( catalog );
        return data;
    }

    /**
     * 添加目录信息
     *
     * @param catalogName
     * @param catalogPId
     * @param catalogLevel
     * @return
     */
    @RequestMapping(value = "saveCatalog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result saveCatalog(@RequestParam("catalogName") String catalogName
            , @RequestParam("catalogPId") String catalogPId
            , @RequestParam("catalogLevel") String catalogLevel) {
        //System.out.println( catalogName + ":   " + catalogPId + "  " + catalogLevel );
        if (catalogName.isEmpty() && catalogPId.isEmpty() && catalogLevel.isEmpty()) {
            return DefaultResult.fail();
        }
        //保存返回结果
        Boolean res = null;
        //判断目录等级
        if ("3".equals( catalogLevel )) {

            TcsysCatalog3 tcsysCatalog3 = new TcsysCatalog3();
            tcsysCatalog3.setCatalogName( catalogName );
            tcsysCatalog3.setCatalog2Id( catalogPId );
            //保存
            res = catalogService.saveCatalog3Info( tcsysCatalog3 );
        } else if ("2".equals( catalogLevel )) {
            TcsysCatalog2 tcsysCatalog2 = new TcsysCatalog2();
            tcsysCatalog2.setCatalogName( catalogName );
            tcsysCatalog2.setCatalog1Id( catalogPId );
            //保存
            res = catalogService.saveCatalog2Info( tcsysCatalog2 );

        } else if ("1".equals( catalogLevel )) {
            TcsysCatalog1 tcsysCatalog1 = new TcsysCatalog1();
            tcsysCatalog1.setCatalogName( catalogName );
            //保存
            res = catalogService.saveCatalog1Info( tcsysCatalog1 );
        }
        return res ? DefaultResult.success() : DefaultResult.fail();
    }


    /**
     * 根据id 和 catalogLevel获取对应的目录信息
     *
     * @param id
     * @param catalogLevel
     * @return
     */
    @RequestMapping("queryCatalog")
    @ResponseBody
    public ResultData queryCatalog(String id, String catalogLevel) {
        //判断catalogLevel，选择查询的目录
        TcsysCatalogGradeVO tcsysCatalogGradeVO = catalogService.getCatalogByIdAndLevel( id, catalogLevel );
        return DefaultResultData.data( tcsysCatalogGradeVO );
    }

    /**
     * 根据传入的参数更新目录
     *
     * @param catalogDelete
     * @param catalogName
     * @param catalogLevel
     * @param id
     * @return
     */
    @RequestMapping(value = "updateCatalog", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result updateCatalog(@RequestParam("catalogDelete") String catalogDelete
            , @RequestParam("catalogName") String catalogName
            , @RequestParam("catalogLevel") String catalogLevel
            , @RequestParam("id") String id) {
        Boolean res = catalogService.updateCatalog( catalogDelete, catalogName, catalogLevel, id );
        return DefaultResult.success();
    }


    @RequestMapping(value = "catalogInfo/{id}/{catalogLevel}",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteCatalog(@PathVariable("id") Integer id,@PathVariable("catalogLevel") Integer catalogLevel){
        Boolean res = catalogService.deleteCatalog(id,catalogLevel);
        return res?DefaultResult.success():DefaultResult.fail();
    }

}
