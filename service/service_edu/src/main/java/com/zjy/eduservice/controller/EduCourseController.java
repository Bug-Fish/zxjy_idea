package com.zjy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.commonutils.R;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.EduTeacher;
import com.zjy.eduservice.entity.vo.CourseInfoVo;
import com.zjy.eduservice.entity.vo.CoursePublishVo;
import com.zjy.eduservice.entity.vo.CourseQuery;
import com.zjy.eduservice.entity.vo.TeacherQuery;
import com.zjy.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @PostMapping("/addCourse")
    public R addCourse(@RequestBody EduCourse eduCourse) {
        courseService.save(eduCourse);
        return R.ok().data("eduCourse", eduCourse);
    }
    @DeleteMapping("/deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id) {
        courseService.removeCourse(id);
        return R.ok();
    }
    @PostMapping("/updateCourse")
    public R updateCourse(@RequestBody EduCourse eduCourse) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("id", eduCourse.getId());
        courseService.update(eduCourse, wrapper);
        return R.ok().data("eduCourse", eduCourse);
    }
    @GetMapping("/getCourse")
    public R getCourse() {
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list", list);
    }

    @PostMapping("/pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        if (! StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (! StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacherId", teacherId);
        }
        if (! StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subjectParentId", subjectParentId);
        }
        if (! StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subjectId", subjectId);
        }

        wrapper.orderByDesc("gmt_create");

        courseService.page(page, wrapper);

        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();

        return R.ok().data("total", total).data("records", records);
    }




    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getAllCourseInfoById(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId) {
        CoursePublishVo coursePublishVo = courseService.getPublicCourseInfo(courseId);
        return R.ok().data("coursePublishVo", coursePublishVo);
    }

    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        CourseInfoVo courseInfoVo1 = courseService.updateAllCourseInfoById(courseInfoVo);
        return R.ok().data("courseInfoVo1", courseInfoVo1);
    }

    @PostMapping("/publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");

        courseService.updateById(eduCourse);
        return R.ok();
    }

    @DeleteMapping("/deleteCourseInfo/{courseId}")
    public R deleteCourseInfo(@PathVariable String courseId) {
        boolean b = courseService.deleteAllCourseInfoById(courseId);
        return R.ok().data("courseInfoVo", b);
    }





}

//    SELECT ec.id, ec.title, ec.price , ec.lesson_num ,
//        ecd . description,
//        et.name ,
//        es1.title As onesubject,es2.title As two subject
//        FROM edu_course ec LEFT OUTER JOIN edu_course_description ecd oN ec.id=ecd.id
//        工EFTOUTERJOIN edu _teacher et oNec.teacher_id=et.id
//        LEFTOUTERJOIN edu_subject es1 ON ec.subject_parent_id=es1.idLEFT OUTERJOIN edu_subject es2 ON ec.subject_id=es2.id
//        wHERE ec.id='1234747900958183425'
