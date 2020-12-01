package com.zjy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.eduservice.entity.EduComment;
import com.zjy.eduservice.mapper.EduCommentMapper;
import com.zjy.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-14
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public Map<String, Object> getAllComment(Page<EduComment> page) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_modified");
        baseMapper.selectPage(page, wrapper);
        List<EduComment> records = page.getRecords();
        boolean hasPrevious = page.hasPrevious();
        boolean hasNext = page.hasNext();
        long current = page.getCurrent();
        long total = page.getTotal();
        long size = page.getSize();
        long pages = page.getPages();
        map.put("records", records);
        map.put("hasPrevious", hasPrevious);
        map.put("hasNext", hasNext);
        map.put("current", current);
        map.put("total", total);
        map.put("size", size);
        map.put("pages", pages);
        return map;
    }
}
