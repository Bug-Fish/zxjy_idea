package com.zjy.eduservice.controller;


import com.zjy.commonutils.R;
import com.zjy.eduservice.entity.EduChapter;
import com.zjy.eduservice.entity.chapter.ChapterVo;
import com.zjy.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {
    @Autowired
    EduChapterService chapterService;
    @GetMapping("/getChapter/{courseId}")
    public R getChapter(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getAllChapter(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    //添加章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        EduChapter chapter = chapterService.addOneChapter(eduChapter);
        return R.ok().data("chapter", chapter);
    }

    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        EduChapter chapter = chapterService.updateOneChapter(eduChapter);
        return R.ok().data("chapter", chapter);
    }

    @DeleteMapping("/deleteChapter/{courseId}")
    public R deleteChapter(@PathVariable String courseId) {
        int chapter = chapterService.deleteOneChapter(courseId);
        return R.ok().data("chapter", chapter);
    }

}

