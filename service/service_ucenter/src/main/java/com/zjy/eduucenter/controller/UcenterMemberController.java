package com.zjy.eduucenter.controller;


import com.zjy.commonutils.JwtUtils;
import com.zjy.commonutils.R;
import com.zjy.commonutils.ordervo.UcenterMemberOrder;
import com.zjy.eduucenter.entity.UcenterMember;
import com.zjy.eduucenter.entity.vo.RegisterVo;
import com.zjy.eduucenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/eduucenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(id);
        return R.ok().data("userInfo", member);
    }

    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfo(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder memberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member, memberOrder);
        return memberOrder;
    }

    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("count", count);
    }
}

