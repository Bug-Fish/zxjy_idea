package com.zjy.eduservice.service;

import com.zjy.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-31
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);
}
