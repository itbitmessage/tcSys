package com.gzmu.tcsys.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gzmu.tcsys.bean.TcsysAttrInfo;
import com.gzmu.tcsys.bean.TcsysAttrValue;
import com.gzmu.tcsys.commmon.DefaultResult;
import com.gzmu.tcsys.commmon.DefaultResultData;
import com.gzmu.tcsys.commmon.Result;
import com.gzmu.tcsys.commmon.ResultData;
import com.gzmu.tcsys.service.TcsysAttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台属性controller
 *
 * @author weicaiwang
 */
@Controller
//解决跨域问题
@CrossOrigin
public class TcsysAttrController {


    @Reference
    TcsysAttrService tcsysAttrService;

    /**
     * 获取属性信息
     * @param catalog3Id
     * @return
     */
    @RequestMapping("attrInfoList")
    @ResponseBody
    public ResultData tcsysAttrInfoList(String catalog3Id){
        List<TcsysAttrInfo> tcsysAttrInfoList = tcsysAttrService.getTcsysAttrInfo(catalog3Id);
        return DefaultResultData.data( tcsysAttrInfoList );
    }

    @GetMapping("getAttrInfoById/{id}")
    @ResponseBody
    public ResultData getAttrInfoById(@PathVariable("id") String id){
        TcsysAttrInfo tcsysAttrInfo = tcsysAttrService.getAttrInfoById(id);
        return DefaultResultData.data( tcsysAttrInfo );
    }

    @PutMapping("editAttrInfoName/{id}/{productAttrName}")
    @ResponseBody
    public Result editAttrInfoName(@PathVariable("id") String id,
                                   @PathVariable("productAttrName") String productAttrName){
        Boolean res = tcsysAttrService.editAttrInfoName(id,productAttrName);
        return res?DefaultResult.success():DefaultResult.fail();
    }

    @DeleteMapping("deleteAttrInfoById/{id}")
    @ResponseBody
    public Result deleteAttrInfoById(@PathVariable("id") String id){
        Boolean res = tcsysAttrService.deleteAttrInfoById(id);
        return res?DefaultResult.success():DefaultResult.fail();
    }

    @PostMapping("saveAttrInfo")
    @ResponseBody
    public Result saveAttrInfo(@RequestBody TcsysAttrInfo tcsysAttrInfo){
        Boolean res = tcsysAttrService.saveAttrInfo(tcsysAttrInfo);
        return res?DefaultResult.success():DefaultResult.fail();
    }


    @PutMapping(value = "saveAttrValue/{id}")
    @ResponseBody
    public Result saveAttrValue(@PathVariable("id") String id,@RequestBody String tcsysAttrValueList){
        Boolean res = tcsysAttrService.saveAttrValue(id,tcsysAttrValueList);
        return res?DefaultResult.success():DefaultResult.fail();
    }
}
