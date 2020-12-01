package com.zjy.eduucenter.service;

import com.zjy.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.eduucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    Integer countRegisterDay(String day);

}
