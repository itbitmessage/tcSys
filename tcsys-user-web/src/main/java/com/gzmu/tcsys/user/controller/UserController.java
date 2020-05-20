package com.gzmu.tcsys.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gzmu.tcsys.bean.Member;
import com.gzmu.tcsys.commmon.*;
import com.gzmu.tcsys.service.UserService;
import com.gzmu.tcsys.vo.TcsysMemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author weicaiwang
 */
@Controller
//解决跨域问题
@CrossOrigin
public class UserController {

    @Reference
    UserService userService;

    /**
     * 根据用户输入的信息进行member的分页查询
     *
     * @param member
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("member/{pageNum}/{pageSize}/{startTime}/{endTime}")
    @ResponseBody
    public ResultData getMember(Member member
            , @PathVariable("pageNum") Integer pageNum
            , @PathVariable("pageSize") Integer pageSize
            , @PathVariable("startTime") String startTime
            , @PathVariable("endTime") String endTime) {
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
            pageSize = 10;
        }
        if (startTime.equals( "NaN" )){
            //1972-06-19
            startTime = "77771564221";
            //3000-01-01
            endTime = "32503680000000";
        }
        String start = DateFormate.numDateToDate( Long.parseLong( startTime ) );
        String end = DateFormate.numDateToDate( Long.parseLong( endTime ) );


        TcsysMemberVO tcsysMemberVO = userService.getMember( member, pageNum, pageSize, start, end );
        return DefaultResultData.data( tcsysMemberVO );
    }

    /**
     * 保存member
     *
     * @param member
     * @return
     */
    @PostMapping("member")
    @ResponseBody
    public Result add(Member member) {

        Boolean res = userService.add(member);
        return res?DefaultResult.success():DefaultResult.fail();
    }

    /**
     * 根据id删除会员信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("member/{id}")
    @ResponseBody
    public Result deleteMember(@PathVariable("id") String id) {
        Boolean res = userService.deleteMember(id);
        return res?DefaultResult.success():DefaultResult.fail();
    }

    /**
     * 更新会员信息
     * @param member
     * @return
     */
    @PutMapping("member")
    @ResponseBody
    public Result update(Member member){
        Boolean res =  userService.update(member);
        return res?DefaultResult.success():DefaultResult.fail();
    }
}
