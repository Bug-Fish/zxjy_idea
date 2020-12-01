package com.zjy.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.commonutils.R;
import com.zjy.eduservice.entity.EduComment;
import com.zjy.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-14
 */
@RestController
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;
    @GetMapping("/getAllComment/{current}/{limit}")
    public R getAllComment(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduComment> page = new Page<>(current, limit);
        Map<String, Object> map = commentService.getAllComment(page);
        return R.ok().data(map);
    }
}

