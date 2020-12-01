package com.zjy.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.commonutils.R;
import com.zjy.educms.entity.CrmBanner;
import com.zjy.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-08
 */
@RestController
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("getAllBanner")
    public R pageBanner() {

        List<CrmBanner> list = bannerService.selectAllBanner();

        return R.ok().data("list", list);
    }
}

