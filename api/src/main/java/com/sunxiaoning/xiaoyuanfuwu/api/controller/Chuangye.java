package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ChuangyeService;
import com.sunxiaoning.xiaoyuanfuwu.api.service.WorkService;
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

@Api("创业服务管理")
@RestController
@RequestMapping(value = "/api/chuangye", method = RequestMethod.POST)
public class Chuangye extends BaseController {

    String filePath = PropertiesTools.applicationProperty("app.image.url");

    @Autowired
    private ChuangyeService chuangyeService;


    @ApiOperation(value = "添加创业服务", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "chuangye_name", value = "公司名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "chuangye_con", value = "公司简介", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addcahungye")
    public APIResponse addChuangye(@RequestParam(value = "token") String token, /* token */
                               @RequestParam(value = "chuangye_name") String chuangye_name, /* 兼职名称 */
                               @RequestParam(value = "chuangye_con") String chuangye_con
    ) {

        logger.info("================= 添加兼职/招聘接口 =================");
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
        String chuangye_user = tokenUser.getString("username");
        HashMap<String, Object> chuanye = new HashMap<>();
        String chuangye_id = Common.UUID(); //生成购物车的id
        chuanye.put("chuangye_user", chuangye_user);
        chuanye.put("chuangye_id", chuangye_id);
        chuanye.put("chuangye_name", chuangye_name);
        chuanye.put("chuangye_create_time", DateTools.timesNow());
        chuanye.put("chuangye_update_time", DateTools.timesNow());
        chuanye.put("chuangye_con", chuangye_con);



        int f = chuangyeService.addChuangye(chuanye);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }

    }

    @ApiOperation(value = "返回所有创业服务", notes = "")
    @ApiImplicitParams({
    })

    @RequestMapping(value = "/selchuangye")
    public APIResponse selChuangye() {

        logger.info("================= 兼职/招聘列表接口 =================");



        List chuangyelist = chuangyeService.selChuangye();


        return this.success(chuangyelist);
    }

    @ApiOperation(value = "兼职/招聘的详情", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "work_id", value = "work_id", required = true, dataType = "String"),
    })

    @RequestMapping(value = "/findwork")
    public APIResponse findWork(
            @RequestParam(value = "work_id") String work_id
    ) {

        logger.info("================= 兼职/招聘的详情 =================");
        HashMap workmes = chuangyeService.findWork(work_id);
//       return null;
        return this.success(workmes);
    }


    @ApiOperation(value = "报名兼职/招聘", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_id", value = "work_id", required = true, dataType = "String")
    })

    @RequestMapping(value = "/baoming")
    public APIResponse baoming(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "work_id") String work_id
    ) {


        logger.info("================= 报名兼职/招聘 =================");
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

        HashMap<String, Object> userwork = new HashMap<>();
        String user_work_id = Common.UUID();
        userwork.put("user_work_id", user_work_id);
        userwork.put("user_id", user_id);
        userwork.put("work_id", work_id);
        userwork.put("user_work_state", "0");
        Integer f = chuangyeService.baoMing(userwork);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }
    }

}
