package com.zjy.eduorder.service.impl;

import com.zjy.commonutils.ordervo.CourseWebVoOrder;
import com.zjy.commonutils.ordervo.UcenterMemberOrder;
import com.zjy.eduorder.client.EduClient;
import com.zjy.eduorder.client.UcenterClient;
import com.zjy.eduorder.entity.Order;
import com.zjy.eduorder.mapper.OrderMapper;
import com.zjy.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjy.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author Education_ZH
 * @since 2020-11-16
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        //远程调用
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        UcenterMemberOrder userInfo = ucenterClient.getUserInfo(memberId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
