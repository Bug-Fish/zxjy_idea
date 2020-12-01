package com.zjy.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjy.commonutils.JwtUtils;
import com.zjy.commonutils.R;
import com.zjy.eduorder.client.EduClient;
import com.zjy.eduorder.client.UcenterClient;
import com.zjy.eduorder.entity.Order;
import com.zjy.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-16
 */
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        String orderNo = orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderNo", orderNo);
    }

    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }

    @GetMapping("hasBuyCourse/{courseId}/{memberId}")
    public boolean hasBuyCourse(@PathVariable String courseId, @PathVariable String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);
        int count = orderService.count(wrapper);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}

