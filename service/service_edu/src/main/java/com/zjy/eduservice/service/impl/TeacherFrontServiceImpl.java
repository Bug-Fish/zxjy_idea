package com.zjy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.eduservice.entity.EduTeacher;
import com.zjy.eduservice.entity.EduVideo;
import com.zjy.eduservice.mapper.TeacherFrontMapper;
import com.zjy.eduservice.service.TeacherFrontService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherFrontServiceImpl extends ServiceImpl<TeacherFrontMapper, EduTeacher> implements TeacherFrontService {

    @Override
    public Map<String, Object> pageAllTeacher(Long current1, Long limit) {
        Page<EduTeacher> page = new Page<>(current1, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        baseMapper.selectPage(page, wrapper);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        long pages = page.getPages();
        long size = page.getSize();
        long current = page.getCurrent();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("records", records);
        map.put("total", total);
        map.put("pages", pages);
        map.put("size", size);
        map.put("current", current);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
