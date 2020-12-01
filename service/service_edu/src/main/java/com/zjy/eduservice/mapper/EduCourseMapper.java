package com.zjy.eduservice.mapper;

import com.zjy.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjy.eduservice.entity.vo.CoursePublishVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublicCourseInfo(String courseId);
}
