package com.gzmu.tcsys.user.controller;

import com.gzmu.tcsys.service.UserService;
import com.gzmu.tcsys.bean.Member;
import com.gzmu.tcsys.bean.MemberReceiveAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author weicaiwang
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("getAllUser")
    @ResponseBody
    public List<Member> getAllUser(){
        List<Member> Members = userService.getAllUser();
        return Members;
    }

    @RequestMapping("getReceiveAddressByMemberId")
    @ResponseBody
    public List<MemberReceiveAddress> getReceiveAddressByMemberId( String memberId){
        List<MemberReceiveAddress> MemberReceiveAddress = userService.getReceiveAddressByMemberId(memberId);
        return MemberReceiveAddress;
    }


    @RequestMapping("index")
    @ResponseBody
    public String index(){
        return "hello user";
    }


}
