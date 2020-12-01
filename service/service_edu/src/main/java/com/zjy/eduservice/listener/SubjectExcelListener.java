package com.zjy.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.eduservice.entity.EduSubject;
import com.zjy.eduservice.entity.excel.SubjectData;
import com.zjy.eduservice.service.EduSubjectService;
import com.zjy.servicebase.exceptionhandler.ZjyException;
import org.apache.ibatis.annotations.One;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new ZjyException(20001, "文件数据为空");

        }
        EduSubject OneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
    //    EduSubject TwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName());
        if (OneSubject == null) {
            OneSubject = new EduSubject();
            OneSubject.setParentId("0");
            OneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(OneSubject);

        }
        String pid = OneSubject.getId();

        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);

        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }



    }



    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);

    }
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
