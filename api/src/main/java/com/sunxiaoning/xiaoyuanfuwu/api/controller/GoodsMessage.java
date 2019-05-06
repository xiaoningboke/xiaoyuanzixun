package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxiaoning.xiaoyuanfuwu.api.service.GoodsMessageService;
import com.sunxiaoning.xiaoyuanfuwu.common.controller.BaseController;
import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import com.sunxiaoning.xiaoyuanfuwu.tools.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Api("商品管理")
@RestController
@RequestMapping(value = "/api/goodsmessage", method = RequestMethod.POST)
public class GoodsMessage extends BaseController {

    String filePath = PropertiesTools.applicationProperty("app.image.url");
    public static final int ZAISHOU = 0;
    public static final int DAISHOU = 1;
    public static final int XIAJIA = 2;


    @Autowired
    private GoodsMessageService goodsMessageService;

    /**
     * 发布产品的接口
     *
     * @param token    token
     * @param name     商品名称
     * @param number   商品数量
     * @param price    商品价格
     * @param address  所在地址
     * @param state    状态（0在售，1待售，2已下架）
     * @param classify 商品分类
     * @param content  商品描述
     * @param pic      商品图片
     * @return
     */
    @ApiOperation(value = "发布商品", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "token"),
            @ApiImplicitParam(name = "name", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "number", value = "商品数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "商品价格", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "所在地址", required = false, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "状态（0在售，1待售，2已下架）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify", value = "商品分类", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "商品描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pic", value = "商品图片", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addgoods")
    public APIResponse exitusermessage(@RequestParam(value = "token") String token, /* token*/
                                       @RequestParam(value = "name") String name, /* 商品名称 */
                                       @RequestParam(value = "number") String number, /* 商品数量 */
                                       @RequestParam(value = "price") String price, /* 商品价格 */
                                       @RequestParam(value = "address") String address, /* 所在地址 */
                                       @RequestParam(value = "state") Integer state, /* 状态（0在售，1待售，2已下架） */
                                       @RequestParam(value = "classify") String classify, /* 商品分类 */
                                       @RequestParam(value = "content") String content, /* 商品分类 */
                                       @RequestParam(value = "pic") String pic/* 商品图片 */
    ) {

        logger.info("================= 发布产品 =================");

        try {
            if (!Common.validString(token)) {
                return this.fail("用户凭证错误");
            }

            String tokenParsed = JwtTools.parseToken(token);
            if (tokenParsed == null) {
                return this.fail("用户凭证错误");
            }

            // 格式化用户信息
            JSONObject tokenUser = JSON.parseObject(tokenParsed);
            String user_id = tokenUser.getString("user_id");

            HashMap<String, Object> goods = new HashMap<>();
            String goods_id = Common.UUID(); //生成
            goods.put("goods_id", goods_id);
            goods.put("user_id", user_id);
            goods.put("name", name);
            goods.put("number", number);
            goods.put("price", price);
            goods.put("address", address);
            goods.put("state", state);
            goods.put("classify", classify);
            goods.put("content", content);
            /*  ArrayList pictures = new ArrayList<String>(Arrays.asList(pic.split(",")));*/
            String picture = FileTools.saveBase64Image(pic);
            //ArrayList piclist = FileTools.saveBase64Images(pic);
            //String pics = StringUtils.join(piclist, ',');
            goods.put("pic", picture);
            Integer f = goodsMessageService.addGoods(goods);
            if (f > 0) {
                return this.success("添加成功");
            } else {
                return this.fail("添加失败");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return this.fail("操作失败");
        }
    }

    /**
     * 查询所有产品
     *
     * @param shop 产品信息
     * @return
     */
    @ApiOperation(value = "查询商品列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shop", value = "查询信息", required = false, dataType = "String")
    })
    @RequestMapping(value = "/goodslist")
    public APIResponse goodslist(@RequestParam(value = "shop", defaultValue = "") String shop /*商品信息*/
    ) {

        logger.info("================= 查询商品接口 =================");
        List goodslist = goodsMessageService.selGoods(shop);
        if (goodslist.size() == 0) {
            return this.fail("暂无商品");
        }
        for (int i = 0; i < goodslist.size(); i++) {
            HashMap<String, Object> goods = (HashMap) goodslist.get(i);
            //格式化图片
            /*if (goods.get("pic") != null && goods.get("pic").toString().length() > 0) {
                String[] picArr = goods.get("pic").toString().split(",");
                StringBuffer picStr = new StringBuffer();
                for (int j = 0; j < picArr.length; j++) {
                    if (j == 0) {
                        picStr.append(filePath + picArr[j]);
                    } else {
                        picStr.append("," + filePath + picArr[j]);
                    }
                }
                String pic = picStr.toString();
                goods.put("pic", pic);
            }*/
            if (goods.get("pic") != null && goods.get("pic").toString().length() > 0) {
                goods.put("pic", filePath + goods.get("pic"));
            } else {
                goods.put("pic", "");
            }

        }
        return this.success(goodslist);
    }

    /**
     * 查询详细的商品信息以及评论信息
     *
     * @param goods_id 商品的id
     * @return
     */
    @ApiOperation(value = "查询商品详情信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goods_id", value = "商品的id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/goodsmessage")
    public APIResponse goodsmessage(@RequestParam(value = "goods_id", defaultValue = "") String goods_id /*商品的id*/
    ) {

        logger.info("================= 查询商品详情信息接口 =================");
        HashMap<String, Object> goods = goodsMessageService.findGoods(goods_id);
        if (goods != null) {
            ArrayList assess = goodsMessageService.selAssess(goods_id);
            for (int i = 0; i < assess.size(); i++) {
                HashMap<String, Object> m = (HashMap) assess.get(i);

                if (m.get("gapic") != null && m.get("gapic").toString().length() > 0) {
                    String[] picArr = m.get("gapic").toString().split(",");
                    StringBuffer picStr = new StringBuffer();
                    for (int j = 0; j < picArr.length; j++) {
                        if (j == 0) {
                            picStr.append(filePath + picArr[j]);
                        } else {
                            picStr.append("," + filePath + picArr[j]);
                        }
                    }
                    String gapic = picStr.toString();
                    m.put("gapic", gapic);
                }
            }
            //格式化图片
            if (goods.get("pic") != null && goods.get("pic").toString().length() > 0) {
                String[] picArr = goods.get("pic").toString().split(",");
                StringBuffer picStr = new StringBuffer();
                for (int i = 0; i < picArr.length; i++) {
                    if (i == 0) {
                        picStr.append(filePath + picArr[i]);
                    } else {
                        picStr.append("," + filePath + picArr[i]);
                    }
                }
                String pic = picStr.toString();
                goods.put("pic", pic);
            }
            goods.put("assess", assess);
            return this.success(goods);
        } else {
            return this.fail("暂无商品");
        }
    }

    /**
     * 修改商品的信息
     *
     * @param token    token
     * @param goods_id 商品的id
     * @param name     商品名称
     * @param number   商品数量
     * @param price    商品价格
     * @param address  发布地址
     * @param state    状态（0在售，1待售，2已下架）
     * @param classify 商品分类
     * @param content  描述
     * @param pic      图片
     * @return
     */
    @ApiOperation(value = "修改发布的产品", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "token"),
            @ApiImplicitParam(name = "goods_id", value = "goods_id", required = true, dataType = "商品id"),
            @ApiImplicitParam(name = "name", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "number", value = "商品数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "price", value = "商品价格", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "所在地址", required = false, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "状态（0在售，1待售，2已下架）", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classify", value = "商品分类", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "商品描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pic", value = "商品图片", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updategoods")
    public APIResponse updateGoods(@RequestParam(value = "token") String token, /* token*/
                                   @RequestParam(value = "goods_id") String goods_id, /* 商品名称 */
                                   @RequestParam(value = "name") String name, /* 商品名称 */
                                   @RequestParam(value = "number") String number, /* 商品数量 */
                                   @RequestParam(value = "price") String price, /* 商品价格 */
                                   @RequestParam(value = "address") String address, /* 所在地址 */
                                   @RequestParam(value = "state") Integer state, /* 状态（0在售，1待售，2已下架） */
                                   @RequestParam(value = "classify") String classify, /* 商品分类 */
                                   @RequestParam(value = "content") String content, /* 商品分类 */
                                   @RequestParam(value = "pic") String pic/* 商品图片 */
    ) {

        logger.info("================= 发布产品 =================");

        try {
            if (!Common.validString(token)) {
                return this.fail("用户凭证错误");
            }

            String tokenParsed = JwtTools.parseToken(token);
            if (tokenParsed == null) {
                return this.fail("用户凭证错误");
            }

            // 格式化用户信息
            JSONObject tokenUser = JSON.parseObject(tokenParsed);
            String user_id = tokenUser.getString("user_id");

            //判断该商品是不是该用户发布的
            HashMap<String, Object> isGoods = goodsMessageService.isGoods(user_id, goods_id);
            if (isGoods == null) {
                return this.fail("您没有权限");
            }
            HashMap<String, Object> goods = new HashMap<>();
            goods.put("goods_id", goods_id);
            goods.put("user_id", user_id);
            goods.put("name", name);
            goods.put("number", number);
            goods.put("price", price);
            goods.put("address", address);
            goods.put("state", state);
            goods.put("classify", classify);
            goods.put("content", content);
            ArrayList pictures = new ArrayList<String>(Arrays.asList(pic.split(",")));
            ArrayList piclist = FileTools.saveBase64Images(pictures);
            String pics = StringUtils.join(piclist, ',');
            goods.put("pic", pics);
            Integer f = goodsMessageService.updateGoods(goods);
            if (f > 0) {
                return this.success("修改成功");
            } else {
                return this.fail("修改失败");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return this.fail("操作失败");
        }
    }

    @ApiOperation(value = "下架商品", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_id", value = "商品id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/undercarriage")
    public APIResponse undercarriage(@RequestParam(value = "token") String token, /* token */
                                     @RequestParam(value = "goods_id") String goods_id /* 商品id */

    ) {

        logger.info("================= 下架商品 =================");

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

        //判断该商品是不是该用户发布的
        HashMap<String, Object> isGoods = goodsMessageService.isGoods(user_id, goods_id);
        if (isGoods == null) {
            return this.fail("您没有权限");
        }

        int f = goodsMessageService.updateState(goods_id, XIAJIA);
        if (f > 0) {
            return this.success("商品下架");
        } else {
            return this.fail("修改失败");
        }

    }


    /**
     * 评论的接口
     *
     * @param token    token
     * @param goods_id 商品id
     * @param content  评论内容
     * @param pic      评论图片
     * @return
     */
    @ApiOperation(value = "评论", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_id", value = "商品id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pic", value = "评论图片", required = false, dataType = "String")
    })
    @RequestMapping(value = "/comment")
    public APIResponse register(@RequestParam(value = "token") String token, /* token */
                                @RequestParam(value = "goods_id") String goods_id, /* 商品id */
                                @RequestParam(value = "content") String content, /* 评论内容 */
                                @RequestParam(value = "pic", defaultValue = "") String pic /* 评论图片 */
    ) {

        logger.info("================= 评论接口 =================");

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


        HashMap<String, Object> comment = new HashMap<>();
        String assess_id = Common.UUID(); //生成评论的id
        comment.put("assess_id", assess_id);
        comment.put("goods_id", goods_id);
        comment.put("content", content);
        ArrayList pictures = new ArrayList<String>(Arrays.asList(pic.split(",")));
        ArrayList piclist = FileTools.saveBase64Images(pictures);
        String pics = StringUtils.join(piclist, ',');
        comment.put("pic", pics);
        Integer f = goodsMessageService.addComment(comment);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }
    }

    @ApiOperation(value = "添加收藏", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_id", value = "商品id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addcollect")
    public APIResponse addCollect(@RequestParam(value = "token") String token, /* token */
                                  @RequestParam(value = "goods_id") String goods_id /* 商品id */
    ) {

        logger.info("================= 收藏接口 =================");

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


        HashMap<String, Object> collect = new HashMap<>();
        String collect_id = Common.UUID(); //生成收藏的id
        collect.put("collect_id", collect_id);
        collect.put("goods_id", goods_id);
        collect.put("user_id", user_id);
        collect.put("create_time", DateTools.timesNow());
        Integer f = goodsMessageService.addCollect(collect);
        if (f > 0) {
            return this.success("收藏成功");
        } else {
            return this.fail("收藏失败");
        }
    }

    @ApiOperation(value = "收藏列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    })
    @RequestMapping(value = "/collectlist")
    public APIResponse collectList(@RequestParam(value = "token") String token /* token */) {

        logger.info("================= 收藏列表接口 =================");

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


        List collect = goodsMessageService.collectListId(user_id);
        return this.success(collect);

    }


    @ApiOperation(value = "取消收藏", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    })
    @RequestMapping(value = "/delcollect")
    public APIResponse delCollect(@RequestParam(value = "token") String token, /* token */
                                  @RequestParam(value = "goods_id") String goods_id /* token */) {

        logger.info("================= 取消收藏  =================");

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


        Integer i = goodsMessageService.delCollect(user_id, goods_id);
        return this.success("取消成功");

    }


    @ApiOperation(value = "加入购物车", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goods_id", value = "商品id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addshoppingcart")
    public APIResponse addShoppingcart(@RequestParam(value = "token") String token, /* token */
                                       @RequestParam(value = "goods_id") String goods_id /* 商品id */
    ) {

        logger.info("================= 加入购物车 =================");

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


        HashMap<String, Object> collect = new HashMap<>();
        String shoppingcart_id = Common.UUID(); //生成购物车的id
        collect.put("shoppingcart_id", shoppingcart_id);
        collect.put("goods_id", goods_id);
        collect.put("user_id", user_id);
        collect.put("create_time", DateTools.timesNow());
        Integer f = goodsMessageService.addShoppingcart(collect);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }
    }

    @RequestMapping(value = "/shoppingcartlist")
    public APIResponse shoppingcartList(@RequestParam(value = "token") String token /* token */) {

        logger.info("================= 获取购物车列表 =================");

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


        List sc = goodsMessageService.shoppingcartList(user_id);
        for (int i = 0; i < sc.size(); i++) {
            HashMap<String, Object> h = (HashMap) sc.get(i);
            //格式化图片
            if (h.get("pic") != null && h.get("pic").toString().length() > 0) {
                String pic = filePath+h.get("pic").toString();
                h.put("pic", pic);
            }
        }
        return this.success(sc);

    }

    @ApiOperation(value = "取消购物车", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    })
    @RequestMapping(value = "/delshoppingcart")
    public APIResponse delShoppingcart(@RequestParam(value = "token") String token, /* token */
                                       @RequestParam(value = "goods_id") String goods_id /* token */) {

        logger.info("================= 取消收藏  =================");

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


        Integer i = goodsMessageService.delShoppingcart(user_id, goods_id);
        return this.success("取消成功");

    }

}
