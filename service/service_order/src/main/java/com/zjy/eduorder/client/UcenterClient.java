package com.zjy.eduorder.client;

import com.zjy.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @PostMapping("/eduucenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfo(@PathVariable("id") String id);

}
