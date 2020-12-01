package com.zjy.staservice.schedule;

import com.zjy.staservice.service.StatisticsDailyService;
import com.zjy.staservice.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    private StatisticsDailyService dailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task1() {
        System.out.println("**********task1执行了");
        dailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }


}
