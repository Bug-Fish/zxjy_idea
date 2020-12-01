package com.zjy.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-order")
public interface OrdersClient {

    @GetMapping("/eduorder/order/hasBuyCourse/{courseId}/{memberId}")
    public boolean hasBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
