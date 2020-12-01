package com.zjy.eduservice.service;

import com.zjy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.eduservice.entity.vo.CourseInfoVo;
import com.zjy.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getAllCourseInfoById(String courseId);

    CourseInfoVo updateAllCourseInfoById(CourseInfoVo courseInfoVo);

    boolean deleteAllCourseInfoById(String courseId);

    CoursePublishVo getPublicCourseInfo(String courseId);

    void removeCourse(String id);
}
