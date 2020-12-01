package com.zjy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.eduservice.entity.EduChapter;
import com.zjy.eduservice.entity.EduVideo;
import com.zjy.eduservice.entity.chapter.ChapterVo;
import com.zjy.eduservice.entity.chapter.VideoVo;
import com.zjy.eduservice.mapper.EduChapterMapper;
import com.zjy.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.eduservice.service.EduVideoService;
import com.zjy.servicebase.exceptionhandler.ZjyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getAllChapter(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        List<EduVideo> eduVideos = videoService.list(wrapper1);

        List<ChapterVo> finalList = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> videoVoList = new ArrayList<>();
            for (int j = 0; j < eduVideos.size(); j++) {
                EduVideo eduVideo = eduVideos.get(j);
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(eduVideo, videoVo);
                if (eduChapter.getCourseId().equals(eduVideo.getCourseId())) {
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }

        return finalList;
    }

    @Override
    public EduChapter addOneChapter(EduChapter eduChapter) {
        baseMapper.insert(eduChapter);
        return eduChapter;
    }

    @Override
    public EduChapter updateOneChapter(EduChapter eduChapter) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("id", eduChapter.getId());
        int update = baseMapper.update(eduChapter, wrapper);
        if (update == 0) {
            throw new ZjyException(20001, "修改章节失败");
        }
        return eduChapter;
    }

    @Override
    public int deleteOneChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count > 0) {
            throw new ZjyException(20001, "不能删除！");
        } else {
            int i = baseMapper.deleteById(chapterId);
//            if (i == 0) {
//                throw new ZjyException(20001, "删除课程章节失败");
//            }
            return i;
        }

    }

    @Override
    public void removeChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }
}
