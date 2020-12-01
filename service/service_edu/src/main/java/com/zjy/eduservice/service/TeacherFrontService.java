package com.zjy.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.eduservice.entity.EduTeacher;

import java.util.List;
import java.util.Map;

public interface TeacherFrontService extends IService<EduTeacher> {

    Map<String, Object> pageAllTeacher(Long current, Long limit);
}
