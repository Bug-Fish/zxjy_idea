package com.zjy.msmservice.controller;

import com.zjy.commonutils.R;
import com.zjy.msmservice.service.MsmService;
import com.zjy.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        String s = redisTemplate.opsForValue().get(phone);
        if (! StringUtils.isEmpty(s)) {
            return R.ok();
        }

        String code = RandomUtil.getFourBitRandom();
        Map<String, Object>  param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);
        if (! isSend) {
            return R.error().message("短信发送失败");
        } else {
            redisTemplate.opsForValue().set(phone, code, 1, TimeUnit.MINUTES);
            return R.ok();
        }

    }
}
