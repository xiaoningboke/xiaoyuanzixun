package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * 账号 DAO 接口类
 */
public interface GoodsMessageDao {

    Integer addGoods(
            @Param("goods_id") String goods_id,
            @Param("user_id") String user_id,
            @Param("name") String name,
            @Param("number") String number,
            @Param("price") String price,
            @Param("address") String address,
            @Param("state") Integer state,
            @Param("classify") String classify,
            @Param("pic") String pic,
            @Param("content") String content
    );

    List selGoods(@Param("shop") String shop);

    Integer addComment(
            @Param("assess_id") String assess_id,
            @Param("goods_id") String goods_id,
            @Param("content") String content,
            @Param("pic") String pic
    );

    HashMap<String, Object> findGoods(@Param("goods_id") String goods_id);

    ArrayList<Object> selAssess(@Param("goods_id") String goods_id);

    HashMap<String, Object> isGoods(
            @Param("user_id") String user_id,
            @Param("goods_id") String goods_id
    );

    Integer updateGoods(
            @Param("goods_id") String goods_id,
            @Param("user_id") String user_id,
            @Param("name") String name,
            @Param("number") String number,
            @Param("price") String price,
            @Param("address") String address,
            @Param("state") Integer state,
            @Param("classify") String classify,
            @Param("pic") String pic,
            @Param("content") String content
    );

    Integer updateState(
            @Param("goods_id") String goods_id,
            @Param("state") int state
    );

    Integer addCollect(
            @Param("collect_id") String collect_id,
            @Param("goods_id") String goods_id,
            @Param("user_id") String user_id,
            @Param("create_time") String create_time
    );

    List collectListId(@Param("user_id") String user_id);

    Integer delCollect(
            @Param("user_id") String user_id,
            @Param("goods_id") String goods_id
    );


    Integer addShoppingcart(
            @Param("shoppingcart_id") String shoppingcart_id,
            @Param("goods_id") String goods_id,
            @Param("user_id") String user_id,
            @Param("create_time") String create_time
    );

    List shoppingcartList(@Param("user_id") String user_id);

    Integer delShoppingcart(
            @Param("user_id") String user_id,
            @Param("goods_id") String goods_id
    );
}
