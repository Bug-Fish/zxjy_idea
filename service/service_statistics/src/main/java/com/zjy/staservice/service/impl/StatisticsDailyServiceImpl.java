package com.zjy.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.R;
import com.zjy.staservice.client.UcenterClient;
import com.zjy.staservice.entity.StatisticsDaily;
import com.zjy.staservice.mapper.StatisticsDailyMapper;
import com.zjy.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-21
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {


    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public void registerCount(String day) {
        R registerR = ucenterClient.countRegister(day);
        Integer count = (Integer) registerR.getData().get("count");

        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(count);
        sta.setDateCalculated(day);

        sta.setCourseNum(RandomUtils.nextInt(100, 200));
        sta.setLoginNum(RandomUtils.nextInt(100, 200));
        sta.setVideoViewNum(RandomUtils.nextInt(100, 200));

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        if (baseMapper.selectOne(wrapper) != null) {
            baseMapper.update(sta, wrapper);
        } else {
            baseMapper.insert(sta);

        }
//        baseMapper.insert(sta);


    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);

        List<StatisticsDaily> list = baseMapper.selectList(wrapper);

        List<String> dateList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();
        for (StatisticsDaily sta : list) {
            dateList.add(sta.getDateCalculated());
            switch (type) {
                case "login_num":
                    dataList.add(sta.getLoginNum());
                    break;
                case "register_num":
                    dataList.add(sta.getRegisterNum());
                    break;
                case "video_view_num":
                    dataList.add(sta.getVideoViewNum());
                    break;
                case "course_num":
                    dataList.add(sta.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dateList);
        map.put("dataList", dataList);
        return map;
    }
}
