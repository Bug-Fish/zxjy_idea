package com.zjy.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjy.commonutils.R;
import com.zjy.educms.entity.CrmBanner;
import com.zjy.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-08
 */
@RestController
@RequestMapping("/educms/banneradmin")
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        bannerService.page(pageBanner, null);

        return R.ok().data("items", pageBanner.getRecords()).data("total", pageBanner.getTotal());
    }

    @GetMapping("pageBanner/{id}")
    public R pageBanner(@PathVariable long id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("items", banner);
    }

    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return R.ok();
    }

    @PostMapping("updateBanner/{id}")
    public R updateBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.updateById(crmBanner);
        return R.ok();
    }

    @DeleteMapping("deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }

}

