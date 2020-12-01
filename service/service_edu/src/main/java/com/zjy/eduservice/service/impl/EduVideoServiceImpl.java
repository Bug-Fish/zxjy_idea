package com.zjy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.R;
import com.zjy.eduservice.client.VodClient;
import com.zjy.eduservice.entity.EduVideo;
import com.zjy.eduservice.mapper.EduVideoMapper;
import com.zjy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {


    @Autowired
    private VodClient vodClient;
    @Override
    public void removeVideoByCourseId(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        videoQueryWrapper.eq("course_id", id);
        videoQueryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(videoQueryWrapper);
        for (EduVideo video:
             eduVideos) {
//            video.getVideoSourceId();
            if (! StringUtils.isEmpty(video.getVideoSourceId())) {
                vodClient.deleteAliyunVideo(video.getVideoSourceId());
            }

        }
        baseMapper.delete(wrapper);

    }
}
