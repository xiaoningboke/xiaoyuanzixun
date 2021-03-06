package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.GoodsMessageDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.GoodsMessageService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class GoodsMessageServiceImpl implements GoodsMessageService {
    @Autowired
    private GoodsMessageDao goodsMessageDao;

    @Override
    public Integer addGoods(HashMap<String, Object> goods) {
        return goodsMessageDao.addGoods(
                goods.get("goods_id").toString(),
                goods.get("user_id").toString(),
                goods.get("name").toString(),
                goods.get("number").toString(),
                goods.get("price").toString(),
                goods.get("address").toString(),
                (Integer) goods.get("state"),
                goods.get("classify").toString(),
                goods.get("pic").toString(),
                goods.get("content").toString()
        );
    }

    @Override
    public List selGoods(String shop) {
        return goodsMessageDao.selGoods(shop);
    }

    @Override
    public Integer addComment(HashMap<String, Object> comment) {
        return goodsMessageDao.addComment(
                comment.get("assess_id").toString(),
                comment.get("goods_id").toString(),
                comment.get("content").toString(),
                comment.get("pic").toString()
        );
    }

    @Override
    public HashMap<String, Object> findGoods(String goods_id) {
        return goodsMessageDao.findGoods(goods_id);
    }

    @Override
    public ArrayList<Object> selAssess(String goods_id) {
        return goodsMessageDao.selAssess(goods_id);
    }

    @Override
    public HashMap<String, Object> isGoods(String user_id, String goods_id) {
        return goodsMessageDao.isGoods(user_id, goods_id);
    }

    /**
     * 修改产品信息
     *
     * @param goods
     * @return
     */
    @Override
    public Integer updateGoods(HashMap<String, Object> goods) {
        return goodsMessageDao.updateGoods(
                goods.get("goods_id").toString(),
                goods.get("user_id").toString(),
                goods.get("name").toString(),
                goods.get("number").toString(),
                goods.get("price").toString(),
                goods.get("address").toString(),
                (Integer) goods.get("state"),
                goods.get("classify").toString(),
                goods.get("pic").toString(),
                goods.get("content").toString()
        );
    }

    @Override
    public Integer updateState(String goods_id, int state) {
        return goodsMessageDao.updateState(goods_id, state);
    }

    /**
     * 添加收藏
     *
     * @param collect
     * @return
     */
    @Override
    public Integer addCollect(HashMap<String, Object> collect) {
        return goodsMessageDao.addCollect(
                collect.get("collect_id").toString(),
                collect.get("goods_id").toString(),
                collect.get("user_id").toString(),
                collect.get("create_time").toString()
        );
    }

    /**
     * 收藏的列表
     *
     * @param user_id
     * @return
     */
    @Override
    public List collectListId(String user_id) {
        return goodsMessageDao.collectListId(user_id);
    }

    /**
     * 取消关注
     *
     * @param user_id
     * @param goods_id
     * @return
     */
    @Override
    public Integer delCollect(String user_id, String goods_id) {
        return goodsMessageDao.delCollect(user_id, goods_id);
    }

    /**
     * 加入到购物车
     *
     * @param shoppingcart
     * @return
     */
    @Override
    public Integer addShoppingcart(HashMap<String, Object> shoppingcart) {
        return goodsMessageDao.addShoppingcart(
                shoppingcart.get("shoppingcart_id").toString(),
                shoppingcart.get("goods_id").toString(),
                shoppingcart.get("user_id").toString(),
                shoppingcart.get("create_time").toString()
        );
    }

    /**
     * 获取购物车列表
     *
     * @param user_id
     * @return
     */
    @Override
    public List shoppingcartList(String user_id) {
        return goodsMessageDao.shoppingcartList(user_id);
    }

    /**
     * 取消购物车
     *
     * @param user_id
     * @param goods_id
     * @return
     */
    @Override
    public Integer delShoppingcart(String user_id, String goods_id) {
        return goodsMessageDao.delShoppingcart(user_id, goods_id);
    }


}
