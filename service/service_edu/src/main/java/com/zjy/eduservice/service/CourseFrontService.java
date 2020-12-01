package com.zjy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.frontvo.CourseFrontVo;
import com.zjy.eduservice.entity.frontvo.CourseWebVo;

import java.util.List;
import java.util.Map;

public interface CourseFrontService extends IService<EduCourse> {
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getFrontCourseInfo(String courseId);
}
