package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 商品业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface GoodsMessageService {

    /**
     * 添加商品
     *
     * @param goods 商品信息
     * @return
     */
    Integer addGoods(HashMap<String, Object> goods);

    /**
     * 查询商品列表
     *
     * @param shop 查询条件
     * @return
     */
    List selGoods(String shop);

    /**
     * 添加商品的评论
     *
     * @param comment 评论的信息
     * @return
     */
    Integer addComment(HashMap<String, Object> comment);

    /**
     * 根据id查询商品的具体信息
     *
     * @param goods_id 商品的id
     * @return
     */
    HashMap<String, Object> findGoods(String goods_id);

    /**
     * 查询评论信息
     *
     * @param goods_id 商品的id
     * @return
     */
    ArrayList<Object> selAssess(String goods_id);

    /**
     * 判断该商品是不是该用户发布的
     *
     * @param user_id
     * @param goods_id
     * @return
     */
    HashMap<String, Object> isGoods(String user_id, String goods_id);

    /**
     * 修改产品的信息
     *
     * @param goods 商品信息
     * @return
     */
    Integer updateGoods(HashMap<String, Object> goods);

    /**
     * 更新商品的状态
     *
     * @param goods_id 商品的id
     * @param state    商品的状态
     * @return
     */
    Integer updateState(String goods_id, int state);

    /**
     * 收藏该商品
     *
     * @param collect
     * @return
     */
    Integer addCollect(HashMap<String, Object> collect);

    /**
     * 查询该人的收藏列表
     *
     * @param user_id
     * @return
     */
    List collectListId(String user_id);

    /**
     * 取消关注
     *
     * @param user_id
     * @param goods_id
     * @return
     */
    Integer delCollect(String user_id, String goods_id);

    /**
     * 加入购物车
     *
     * @param shoppingcart
     * @return
     */
    Integer addShoppingcart(HashMap<String, Object> shoppingcart);

    /**
     * 购物车列表
     *
     * @param user_id
     * @return
     */
    List shoppingcartList(String user_id);

    /**
     * 取消购物车
     *
     * @param user_id
     * @param goods_id
     * @return
     */
    Integer delShoppingcart(String user_id, String goods_id);
}
