package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxiaoning.xiaoyuanfuwu.api.service.AccountService;
import com.sunxiaoning.xiaoyuanfuwu.api.service.WorkService;
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

@Api("兼职招聘管理")
@RestController
@RequestMapping(value = "/api/work", method = RequestMethod.POST)
public class Work extends BaseController {

    String filePath = PropertiesTools.applicationProperty("app.image.url");

    @Autowired
    private WorkService workService;


    @ApiOperation(value = "添加兼职/招聘", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_name", value = "兼职名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_number", value = "兼职人数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_money", value = "工资", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_unit", value = "用人单位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_start_time", value = "工作开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_end_time", value = "工作结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_content", value = "工作描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_type", value = "兼职类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_user", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_phone", value = "联系电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "work_pic", value = "兼职图片", required = true, dataType = "String")
    })
    @RequestMapping(value = "/addwork")
    public APIResponse addWork(@RequestParam(value = "token") String token, /* token */
                                @RequestParam(value = "work_name") String work_name, /* 兼职名称 */
                                @RequestParam(value = "work_number") String work_number, /* 人数 */
                                @RequestParam(value = "work_money") String work_money, /* 工资 */
                                @RequestParam(value = "work_unit") String work_unit, /* 邮箱 */
                                @RequestParam(value = "work_start_time") String work_start_time, /* 工作开始时间 */
                                @RequestParam(value = "work_end_time") String work_end_time, /* 工作结束时间 */
                                @RequestParam(value = "work_content") String work_content, /* 工作描述 */
                                @RequestParam(value = "work_type") String work_type, /* 兼职类型 */
                                @RequestParam(value = "work_user") String work_user, /* 联系人 */
                                @RequestParam(value = "work_phone") String work_phone, /* 联系电话 */
                                @RequestParam(value = "work_pic") String work_pic /* 兼职图片 */
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
        String user_id = tokenUser.getString("user_id");
        HashMap<String, Object> work = new HashMap<>();
        String work_id = Common.UUID(); //生成购物车的id
        work.put("work_id", work_id);
        work.put("user_id", user_id);
        work.put("work_name", work_name);
        work.put("work_number", work_number);
        work.put("work_money", work_money);
        work.put("work_unit", work_unit);
        work.put("work_start_time", work_start_time);
        work.put("work_end_time", work_end_time);
        work.put("work_content", work_content);
        work.put("work_type", work_type);
        work.put("work_user", work_user);
        work.put(work_phone, work_phone);

        String picture = FileTools.saveBase64Image(work_pic);
        work.put("work_pic", picture);
        int f = workService.addWork(work);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }


    }


}
