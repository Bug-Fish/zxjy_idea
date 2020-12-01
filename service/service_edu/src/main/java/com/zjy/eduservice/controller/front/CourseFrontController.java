package com.zjy.eduservice.controller.front;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.commonutils.JwtUtils;
import com.zjy.commonutils.R;
import com.zjy.commonutils.ordervo.CourseWebVoOrder;
import com.zjy.commonutils.ordervo.UcenterMemberOrder;
import com.zjy.eduservice.client.OrdersClient;
import com.zjy.eduservice.entity.EduChapter;
import com.zjy.eduservice.entity.EduCourse;
import com.zjy.eduservice.entity.EduTeacher;
import com.zjy.eduservice.entity.chapter.ChapterVo;
import com.zjy.eduservice.entity.frontvo.CourseFrontVo;
import com.zjy.eduservice.entity.frontvo.CourseWebVo;
import com.zjy.eduservice.service.CourseFrontService;
import com.zjy.eduservice.service.EduChapterService;
import com.zjy.eduservice.service.TeacherFrontService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private CourseFrontService frontService;
    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;
    @PostMapping("pageAllCourse/{current}/{limit}")
    public R pageAllTeacher(@PathVariable long current, @PathVariable long limit,
                            @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        Map<String, Object> map = frontService.getCourseFrontList(pageCourse, courseFrontVo);
        return R.ok().data(map);

    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        CourseWebVo courseWebVo = frontService.getFrontCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getAllChapter(courseId);
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        //boolean hasBuyCourse = ordersClient.hasBuyCourse(courseId, memberIdByJwtToken);
        return R.ok().data("chapterVideoList", chapterVideoList).data("courseWebVo", courseWebVo).data("hasBuyCourse", true);
    }

    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo frontCourseInfo = frontService.getFrontCourseInfo(id);
        CourseWebVoOrder memberOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(frontCourseInfo, memberOrder);
        return memberOrder;
    }
}
