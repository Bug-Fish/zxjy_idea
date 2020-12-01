package com.zjy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.R;
import com.zjy.eduservice.client.VodClient;
import com.zjy.eduservice.entity.EduVideo;
import com.zjy.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private VodClient vodClient;
    @Autowired
    private EduVideoService videoService;

    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok().data("video", eduVideo);
    }

    @DeleteMapping("/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (! StringUtils.isEmpty(videoSourceId)) {
            vodClient.deleteAliyunVideo(videoSourceId);
        }

        videoService.removeById(videoId);
        return R.ok();
    }

    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", eduVideo.getId());
        videoService.update(eduVideo, wrapper);
        return R.ok().data("video", eduVideo);
    }

    @GetMapping("/getVideo/{courseId}")
    public R getVideo(@PathVariable String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduVideo> list = videoService.list(wrapper);
        return R.ok().data("list", list);
    }
}

