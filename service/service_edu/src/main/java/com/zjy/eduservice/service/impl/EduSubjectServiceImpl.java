package com.zjy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.eduservice.entity.EduSubject;
import com.zjy.eduservice.entity.excel.SubjectData;
import com.zjy.eduservice.entity.subject.OneSubject;
import com.zjy.eduservice.entity.subject.TwoSubject;
import com.zjy.eduservice.listener.SubjectExcelListener;
import com.zjy.eduservice.mapper.EduSubjectMapper;
import com.zjy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-10-30
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        QueryWrapper<EduSubject> wrapper =new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<EduSubject> list = baseMapper.selectList(wrapper);

        QueryWrapper<EduSubject> wrapper1 =new QueryWrapper<>();
        wrapper.ne("parent_id", "0");
        List<EduSubject> list1 = baseMapper.selectList(wrapper1);

        List<OneSubject> finalList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(list.get(i).getId());
            oneSubject.setTitle(list.get(i).getTitle());

            List<TwoSubject> twoSubjectList = new ArrayList<>();
            for (int j = 0; j < list1.size(); j++) {
                EduSubject tSubject = list1.get(j);
                if (tSubject.getParentId().equals(list.get(i).getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjectList);

            finalList.add(oneSubject);
        }

//        for (int i = 0; i < list1.size(); i++) {
//            OneSubject oneSubject = new OneSubject();
//            BeanUtils.copyProperties(list1.get(i), oneSubject);
//            finalList.add(oneSubject);
//        }

        return finalList;
    }
}
