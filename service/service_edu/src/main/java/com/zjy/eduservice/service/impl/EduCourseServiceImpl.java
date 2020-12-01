package com.zjy.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.EduCourseDescription;
import com.zjy.eduservice.entity.EduVideo;
import com.zjy.eduservice.entity.vo.CourseInfoVo;
import com.zjy.eduservice.entity.vo.CoursePublishVo;
import com.zjy.eduservice.mapper.EduCourseMapper;
import com.zjy.eduservice.service.EduChapterService;
import com.zjy.eduservice.service.EduCourseDescriptionService;
import com.zjy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.eduservice.service.EduVideoService;
import com.zjy.servicebase.exceptionhandler.ZjyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private EduCourseService courseService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean insert = this.save(eduCourse);
        if (! insert) {
            throw new ZjyException(20001, "添加课程信息失败");
        }


        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        descriptionService.save(eduCourseDescription);

        return eduCourse.getId();
    }

    @Override
    public CourseInfoVo getAllCourseInfoById(String courseId) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        QueryWrapper<EduCourseDescription> wrapper1 = new QueryWrapper<>();
        wrapper.eq("id",courseId);
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        List<EduCourseDescription> list = descriptionService.list(wrapper1);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(courseInfoVo.getId())) {
                courseInfoVo.setDescription(list.get(i).getDescription());
            }
        }
        return courseInfoVo;
    }

    @Override
    public CourseInfoVo updateAllCourseInfoById(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new ZjyException(20001, "修改课程信息失败");
        }
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, description);
        boolean b = descriptionService.updateById(description);
        if (! b) {
            throw new ZjyException(20001, "修改课程描述信息失败");
        }

        return courseInfoVo;


    }

    @Override
    public boolean deleteAllCourseInfoById(String courseId) {
        int delete = baseMapper.deleteById(courseId);
        boolean b = descriptionService.removeById(courseId);
        if (! b || delete == 0) {
            throw new ZjyException(20001, "删除课程信息失败");
        }
        return true;
    }

    @Override
    public CoursePublishVo getPublicCourseInfo(String courseId) {
        CoursePublishVo publicCourseInfo = baseMapper.getPublicCourseInfo(courseId);
        return publicCourseInfo;
    }

    @Override
    public void removeCourse(String id) {
        videoService.removeVideoByCourseId(id);
        chapterService.removeChapterByCourseId(id);
        descriptionService.removeById(id);
        int i = baseMapper.deleteById(id);
        if (i == 0) {
            throw new ZjyException(20001, "删除失败");
        }
    }
}
