package com.zjy.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.commonutils.R;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.EduTeacher;
import com.zjy.eduservice.service.EduCourseService;
import com.zjy.eduservice.service.TeacherFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private TeacherFrontService frontService;
    @Autowired
    private EduCourseService courseService;
    @PostMapping("pageAllTeacher/{current}/{limit}")
    public R pageAllTeacher(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        Map<String, Object> map = frontService.pageAllTeacher(current, limit);
//        List<EduTeacher> records = iPage.getRecords();
//        long total = iPage.getTotal();
//        long current1 = iPage.getCurrent();
        return R.ok().data(map);

    }
    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id) {
        EduTeacher teacher = frontService.getById(id);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        List<EduCourse> list = courseService.list(wrapper);

        return R.ok().data("teacher", teacher).data("courseList", list);
    }

}
