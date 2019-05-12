package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxiaoning.xiaoyuanfuwu.api.service.WorkService;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ZixunService;
import com.sunxiaoning.xiaoyuanfuwu.common.controller.BaseController;
import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import com.sunxiaoning.xiaoyuanfuwu.tools.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@Api("兼职招聘管理")
@RestController
@RequestMapping(value = "/api/zixun", method = RequestMethod.POST)
public class Zixun extends BaseController {

    String filePath = PropertiesTools.applicationProperty("app.image.url");

    @Autowired
    private ZixunService zixunService;


    @ApiOperation(value = "添加兼职/招聘", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "news_title", value = "标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "news_classify", value = "分类", required = true, dataType = "String"),
            @ApiImplicitParam(name = "new_pic", value = "图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "news_con", value = "内容", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addnews")
    public APIResponse addNews(@RequestParam(value = "token") String token, /* token */
                               @RequestParam(value = "news_title") String news_title, /* 标题 */
                               @RequestParam(value = "news_classify") String news_classify, /* 分类 */
                               @RequestParam(value = "new_pic") String new_pic, /* 图片 */
                               @RequestParam(value = "news_con") String news_con /* 内容 */

    ) {

        logger.info("================= 添加新闻资讯 =================");
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
        String username = tokenUser.getString("username");
        HashMap<String, Object> news = new HashMap<>();
        String news_id = Common.UUID(); //生成购物车的id
        news.put("news_id", news_id);
        news.put("news_user_id", user_id);
        news.put("news_title", news_title);
        news.put("news_user", username);
        news.put("news_time", DateTools.timesNow());
        news.put("news_exit_time", DateTools.timesNow());
        news.put("news_classify", news_classify);
        news.put("news_con", news_con);

        String pic = FileTools.saveBase64Image(new_pic);
        news.put("new_pic", pic);
        int f = zixunService.addZixun(news);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }

    }

    @ApiOperation(value = "返回所有的兼职/招聘", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "news_classify", value = "分类", required = false, dataType = "String"),
    })

    @RequestMapping(value = "/selnews")
    public APIResponse selNews(
            @RequestParam(value = "news_classify", defaultValue = "") String news_classify
    ) {

        logger.info("================= 新闻资讯列表接口 =================");
        List zixun = zixunService.selZixun(news_classify);

        for (int i = 0; i < zixun.size(); i++) {
            HashMap<String, Object> zixunmessage = (HashMap) zixun.get(i);

            if (zixunmessage.get("new_pic") != null && zixunmessage.get("new_pic").toString().length() > 0) {
                zixunmessage.put("new_pic", filePath + zixunmessage.get("new_pic"));
            } else {
                zixunmessage.put("new_pic", "");
            }
        }


        return this.success(zixun);
    }

    @ApiOperation(value = "新闻资讯的详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "news_id", value = "news_id", required = true, dataType = "String"),
    })

    @RequestMapping(value = "/findnews")
    public APIResponse findNews(
            @RequestParam(value = "news_id") String news_id
    ) {

        logger.info("================= 兼职/招聘的详情 =================");
        HashMap newsmsg = zixunService.findNews(news_id);
        if (newsmsg.get("new_pic") != null && newsmsg.get("new_pic").toString().length() > 0) {
            newsmsg.put("new_pic", filePath + newsmsg.get("new_pic"));
        } else {
            newsmsg.put("new_pic", "");
        }
        return this.success(newsmsg);
    }


    @ApiOperation(value = "发布失物招领", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lost_con", value = "失物招领的内容", required = true, dataType = "String")
    })

    @RequestMapping(value = "/addshiwu")
    public APIResponse addShiwu(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "lost_con") String lost_con
    ) {


        logger.info("================= 发布失物招领 =================");
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
        String lost_user_id = tokenUser.getString("user_id");
        String lost_user = tokenUser.getString("username");

        HashMap<String, Object> shiwu = new HashMap<>();
        String lost_id = Common.UUID();
        shiwu.put("lost_id", lost_id);
        shiwu.put("lost_user", lost_user);
        shiwu.put("lost_user_id", lost_user_id);
        shiwu.put("lost_time", DateTools.timesNow());
        shiwu.put("lost_con", lost_con);
        Integer f = zixunService.addShiwu(shiwu);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }
    }


    @ApiOperation(value = "返回所有的失物招领", notes = "")
    @ApiImplicitParams({
    })

    @RequestMapping(value = "/selshiwu")
    public APIResponse selShiwu(
    ) {

        logger.info("================= 失物招领列表接口 =================");
        List shiwu = zixunService.selShiwu();
        return this.success(shiwu);
    }


    @ApiOperation(value = "失物招领详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "lost_id", value = "lost_id", required = true, dataType = "String"),
    })

    @RequestMapping(value = "/findshiwu")
    public APIResponse findShiwu(
            @RequestParam(value = "lost_id") String lost_id
    ) {

        logger.info("================= 兼职/招聘的详情 =================");
        HashMap shiwu = zixunService.findShiwu(lost_id);

        return this.success(shiwu);
    }

    @ApiOperation(value = "领取失物招领", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "token"),
            @ApiImplicitParam(name = "lost_id", value = "lost_id", required = true, dataType = "String"),
    })

    @RequestMapping(value = "/lingqu")
    public APIResponse lingqu(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "lost_id") String lost_id
    ) {

        logger.info("================= 领取失物招领 =================");
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
        String lost_owner_id = tokenUser.getString("user_id");
        String lost_owner = tokenUser.getString("username");

        HashMap lingqu = new HashMap();
        lingqu.put("lost_id", lost_id);
        lingqu.put("lost_owner_id", lost_owner_id);
        lingqu.put("lost_owner", lost_owner);
        lingqu.put("lost_receive_time", DateTools.timesNow());
        zixunService.lingqu(lingqu);

//        return null;

        return this.success("领取成功");
    }
}
