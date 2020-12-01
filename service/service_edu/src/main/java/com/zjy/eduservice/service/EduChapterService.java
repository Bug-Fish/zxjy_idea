package com.zjy.eduservice.service;

import com.zjy.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getAllChapter(String courseId);

    EduChapter addOneChapter(EduChapter eduChapter);

    EduChapter updateOneChapter(EduChapter eduChapter);

    int deleteOneChapter(String courseId);

    void removeChapterByCourseId(String id);
}
