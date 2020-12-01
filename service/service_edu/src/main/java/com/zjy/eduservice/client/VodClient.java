package com.zjy.eduservice.client;

import com.zjy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod", fallback = VodFeignClient.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/video/deleteAliyunVideo/{id}")
    public R deleteAliyunVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
