package com.zjy.eduservice.controller;

import com.zjy.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://img-blog.csdn.net/20180522175119742");
    }

}
