package com.sunxiaoning.xiaoyuanfuwu.api.service;

import io.swagger.models.auth.In;

import java.util.HashMap;

/**
 * 订单业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface OrderService {

    /**
     * 添加订单
     *
     * @param order 订单内容
     * @return
     */
    Integer addOrder(HashMap<String, Object> order);

    /**
     * 查询该订单是否属于该用户
     *
     * @param order_id 订单id
     * @param user_id  用户id
     * @return
     */
    HashMap<String, Object> findOrderByUid(String order_id, String user_id);

    /**
     * 修改订单状态
     *
     * @param order_id     订单id
     * @param order_status 订单状态
     * @return
     */
    Integer updateOrderStatus(String order_id, String order_status);

}
