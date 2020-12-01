package com.zjy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.EduTeacher;
import com.zjy.eduservice.entity.frontvo.CourseFrontVo;
import com.zjy.eduservice.entity.frontvo.CourseWebVo;
import com.zjy.eduservice.mapper.CourseFrontMapper;
import com.zjy.eduservice.service.CourseFrontService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseFrontServiceImpl extends ServiceImpl<CourseFrontMapper, EduCourse> implements CourseFrontService {
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (! StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) {
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if (! StringUtils.isEmpty(courseFrontVo.getSubjectId())) {
            wrapper.eq("subject_id", courseFrontVo.getSubjectId());
        }
        if (! StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {
            wrapper.orderByDesc("buy_count");
        }
        if (! StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {
            wrapper.orderByDesc("gmt_create");
        }
        if (! StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageCourse, wrapper);
        List<EduCourse> records = pageCourse.getRecords();
        long total = pageCourse.getTotal();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long current = pageCourse.getCurrent();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();
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

    @Override
    public CourseWebVo getFrontCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
