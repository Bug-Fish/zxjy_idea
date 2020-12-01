package com.zjy.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.R;
import com.zjy.servicebase.exceptionhandler.ZjyException;
import com.zjy.vod.service.InitObject;
import com.zjy.vod.service.VodService;
import com.zjy.vod.utils.ConstantVodUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    @PostMapping("/uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }
    @DeleteMapping("/deleteAliyunVideo/{id}")
    public R deleteAliyunVideo(@PathVariable String id) {

        vodService.removeVideo(id);
        return R.ok();
    }

    @DeleteMapping("/deleteBatch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        for (String s :
                videoIdList) {
            vodService.removeVideo(s);
        }

        return R.ok();
    }

    @GetMapping("/getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
            DefaultAcsClient client = InitObject.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth" ,playAuth);
        } catch (Exception e) {
            throw new ZjyException(20001, "获取凭证失败");
        }
    }
}
