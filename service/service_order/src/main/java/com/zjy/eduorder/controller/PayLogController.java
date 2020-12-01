package com.zjy.eduorder.controller;


import com.zjy.commonutils.R;
import com.zjy.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/eduorder/pay-log")
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {
            return R.error().message("支付出错误！");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }

}

