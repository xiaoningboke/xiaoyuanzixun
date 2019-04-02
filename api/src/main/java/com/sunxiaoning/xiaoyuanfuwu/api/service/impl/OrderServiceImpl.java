package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.OrderDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Integer addOrder(HashMap<String, Object> order) {
        return orderDao.addOrder(
                order.get("order_id").toString(),
                order.get("order_status").toString(),
                order.get("goods_id").toString(),
                order.get("buyer_id").toString(),
                order.get("seller_id").toString(),
                order.get("delivery_type").toString(),
                order.get("delivery_status").toString(),
                order.get("remark").toString(),
                order.get("goods_num").toString(),
                order.get("goods_price").toString(),
                order.get("goods_money").toString(),
                order.get("address_id").toString(),
                order.get("express").toString(),
                order.get("create_time").toString()
        );
    }

    @Override
    public HashMap<String, Object> findOrderByUid(String order_id, String user_id) {
        return orderDao.findOrderByUid(order_id, user_id);
    }

    @Override
    public Integer updateOrderStatus(String order_id, String order_status) {
        return orderDao.updateOrderStatus(order_id, order_status);
    }
}
