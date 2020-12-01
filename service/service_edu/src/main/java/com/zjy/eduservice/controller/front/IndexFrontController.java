package com.zjy.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.R;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.EduTeacher;
import com.zjy.eduservice.service.EduCourseService;
import com.zjy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;
    @GetMapping("index")
    public R index() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        wrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(wrapper);

        QueryWrapper<EduTeacher> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapper1);
        return R.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
