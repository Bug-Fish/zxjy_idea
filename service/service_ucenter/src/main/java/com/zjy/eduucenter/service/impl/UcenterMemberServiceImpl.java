package com.zjy.eduucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.JwtUtils;
import com.zjy.eduucenter.entity.UcenterMember;
import com.zjy.eduucenter.entity.vo.RegisterVo;
import com.zjy.eduucenter.mapper.UcenterMemberMapper;
import com.zjy.eduucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.eduucenter.utils.MD5;
import com.zjy.servicebase.exceptionhandler.ZjyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new ZjyException(20001, "密码或用户名不能为空！");

        }

        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);

        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null) {
            throw new ZjyException(20001, "手机号不存在");
        }
        if (! MD5.encrypt(password).equals(ucenterMember.getPassword())) {
            throw new ZjyException(20001, "密码错误！");
        }
        if (ucenterMember.getIsDisabled()) {
            throw new ZjyException(20001, "该用户被禁用！！");
        }
        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());

        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        String code = registerVo.getCode();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)) {
            throw new ZjyException(20001, "密码、用户名、验证码或昵称不能为空，注册失败！");
        }

        String s = redisTemplate.opsForValue().get(mobile);
        if (! code.equals(s)) {
            throw new ZjyException(20001, "验证码错误，注册失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);

        Integer count = baseMapper.selectCount(wrapper);
        if (count == 0) {
            UcenterMember member = new UcenterMember();
            member.setMobile(mobile);
            member.setNickname(nickname);
            member.setPassword(MD5.encrypt(password));
            member.setAvatar("http://localhost:8848/nacos/img/logo-2000-390.svg");
            member.setIsDisabled(false);
            baseMapper.insert(member);
        } else {
            throw new ZjyException(20001, "手机号重复");
        }

    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegister(day);
    }
}
