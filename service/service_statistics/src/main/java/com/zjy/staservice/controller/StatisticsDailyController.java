package com.zjy.staservice.controller;


import com.zjy.commonutils.R;
import com.zjy.staservice.client.UcenterClient;
import com.zjy.staservice.service.StatisticsDailyService;
import org.apache.commons.collections4.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-21
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        statisticsDailyService.registerCount(day);
        return R.ok();
    }

    @PostMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        Map<String, Object> map = statisticsDailyService.getShowData(type, begin, end);
        return R.ok().data(map);
    }
}

