package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxiaoning.xiaoyuanfuwu.api.service.OrderService;
import com.sunxiaoning.xiaoyuanfuwu.common.controller.BaseController;
import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import com.sunxiaoning.xiaoyuanfuwu.tools.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api("订单管理")
@RestController
@RequestMapping(value = "/api/order", method = RequestMethod.POST)
public class Order extends BaseController {


    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "添加订单", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "order_status", value = "订单项状态，0为已提交订单，1为取消订单", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_id", value = "商品id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "seller_id", value = "卖家id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "delivery_type", value = "配送方式;0自己配送，1平台配送", required = true, dataType = "String"),
            @ApiImplicitParam(name = "delivery_status", value = "配送状态1待发货，2，运输中，3已收货，3换货，4退货中，5交易完成", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", required = false, dataType = "String"),
            @ApiImplicitParam(name = "goods_num", value = "商品数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_price", value = "商品单价", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_money", value = "商品的总价", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address_id", value = "收货地址id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "express", value = "快递单号", required = false, dataType = "String")
    })
    @RequestMapping(value = "/addorder")
    public APIResponse addOrder(@RequestParam(value = "token") String token, /* token */
                                @RequestParam(value = "order_status") String order_status, /* 订单项状态，0为已提交订单，1为取消订单 */
                                @RequestParam(value = "goods_id") String goods_id, /* 商品id */
                                @RequestParam(value = "seller_id") String seller_id, /* 卖家id */
                                @RequestParam(value = "delivery_type") String delivery_type, /* 配送方式;0自己配送，1平台配送 */
                                @RequestParam(value = "delivery_status") String delivery_status, /* 配送状态1待发货，2，运输中，3已收货，3换货，4退货中，5交易完成 */
                                @RequestParam(value = "remark") String remark, /* 备注 */
                                @RequestParam(value = "goods_num") String goods_num, /* 商品数量 */
                                @RequestParam(value = "goods_price") String goods_price, /* 商品单价 */
                                @RequestParam(value = "goods_money") String goods_money, /* 商品的总价 */
                                @RequestParam(value = "address_id") String address_id, /* 收货地址id */
                                @RequestParam(value = "express") String express /* 快递单号 */
    ) {

        logger.info("================= 添加订单接口 =================");

        if (!Common.validString(token)) {
            return this.fail("请先进行登录");
        }
        if (!Common.validString(token)) {
            return this.fail("用户凭证错误");
        }

        String tokenParsed = JwtTools.parseToken(token);
        if (tokenParsed == null) {
            return this.fail("用户凭证错误");
        }
        JSONObject tokenUser = JSON.parseObject(tokenParsed);
        String user_id = tokenUser.getString("user_id");


        HashMap<String, Object> order = new HashMap<>();
        String order_id = Common.UUID(); //生成order_id
        order.put("order_id", order_id);
        order.put("order_status", order_status);
        order.put("goods_id", goods_id);
        order.put("buyer_id", user_id);
        order.put("seller_id", seller_id);
        order.put("delivery_type", delivery_type);
        order.put("delivery_status", delivery_status);
        order.put("remark", remark);
        order.put("goods_num", goods_num);
        order.put("goods_price", goods_price);
        order.put("goods_money", goods_money);
        order.put("address_id", address_id);
        order.put("express", express);
        order.put("create_time", DateTools.timesNow());

        Integer f = orderService.addOrder(order);
        if (f > 0) {
            return this.success("操作成功");
        } else {
            return this.success("操作失败");
        }

    }

    @ApiOperation(value = "更改订单的状态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "order_id", value = "订单id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "order_status", value = "订单状态", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateorderstatus")
    public APIResponse updateOrderStatus(@RequestParam(value = "token") String token, /* token */
                                         @RequestParam(value = "order_id") String order_id, /* 订单id */
                                         @RequestParam(value = "order_status") String order_status /* 订单状态 */
    ) {

        logger.info("================= 更改订单的状态 =================");

        if (!Common.validString(token)) {
            return this.fail("请先进行登录");
        }
        if (!Common.validString(token)) {
            return this.fail("用户凭证错误");
        }

        String tokenParsed = JwtTools.parseToken(token);
        if (tokenParsed == null) {
            return this.fail("用户凭证错误");
        }
        JSONObject tokenUser = JSON.parseObject(tokenParsed);
        String user_id = tokenUser.getString("user_id");

        // 查询该订单的所有者
        HashMap<String, Object> order = orderService.findOrderByUid(order_id, user_id);
        if (order == null) {
            return this.fail("您无权限");
        }
        int f = orderService.updateOrderStatus(order_id, order_status);
        if (f > 0) {
            return this.success("修改成功");
        } else {
            return this.fail("修改失败");
        }


    }
}
