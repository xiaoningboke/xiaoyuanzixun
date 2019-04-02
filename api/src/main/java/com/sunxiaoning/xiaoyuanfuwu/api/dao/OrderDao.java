package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.logging.Handler;

/**
 * 账号 DAO 接口类
 */
public interface OrderDao {

    /**
     * 添加订单
     *
     * @param order_id
     * @param order_status
     * @param goods_id
     * @param buyer_id
     * @param seller_id
     * @param delivery_type
     * @param delivery_status
     * @param remark
     * @param goods_num
     * @param goods_price
     * @param goods_money
     * @param address_id
     * @param express
     * @return
     */
    Integer addOrder(
            @Param("order_id") String order_id,
            @Param("order_status") String order_status,
            @Param("goods_id") String goods_id,
            @Param("buyer_id") String buyer_id,
            @Param("seller_id") String seller_id,
            @Param("delivery_type") String delivery_type,
            @Param("delivery_status") String delivery_status,
            @Param("remark") String remark,
            @Param("goods_num") String goods_num,
            @Param("goods_price") String goods_price,
            @Param("goods_money") String goods_money,
            @Param("address_id") String address_id,
            @Param("express") String express,
            @Param("create_time") String create_time
    );

    /**
     * 查询该订单是否属于该用户
     *
     * @param order_id 订单id
     * @param user_id  用户id
     * @return
     */
    HashMap<String, Object> findOrderByUid(
            @Param("order_id") String order_id,
            @Param("user_id") String user_id
    );

    /**
     * 更新订单的状态
     *
     * @param order_id     订单id
     * @param order_status 订单状态
     * @return
     */
    Integer updateOrderStatus(
            @Param("order_id") String order_id,
            @Param("order_status") String order_status
    );

}
