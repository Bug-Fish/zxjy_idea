package com.zjy.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.frontvo.CourseWebVo;

public interface CourseFrontMapper extends BaseMapper<EduCourse> {

    CourseWebVo getBaseCourseInfo(String courseId);
}
