package com.zjy.eduservice.service;

import com.zjy.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjy.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-30
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
