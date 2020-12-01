package com.zjy.eduservice.client;

import com.zjy.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodFeignClient implements VodClient {
    @Override
    public R deleteAliyunVideo(String id) {
        return R.error().message("删除视频出错了！");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("批量删除视频出错了！");
    }
}
