package com.zjy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.eduservice.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-14
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getAllComment(Page<EduComment> page);
}
