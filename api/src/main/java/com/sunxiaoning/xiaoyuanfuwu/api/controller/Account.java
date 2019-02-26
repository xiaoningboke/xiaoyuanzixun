package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.sunxiaoning.xiaoyuanfuwu.api.service.AccountService;
import com.sunxiaoning.xiaoyuanfuwu.common.controller.BaseController;
import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import com.sunxiaoning.xiaoyuanfuwu.tools.Common;
import com.sunxiaoning.xiaoyuanfuwu.tools.DateTools;
import com.sunxiaoning.xiaoyuanfuwu.tools.FileTools;
import com.sunxiaoning.xiaoyuanfuwu.tools.JwtTools;
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

@Api("登录/注册")
@RestController
@RequestMapping(value = "/api/account", method = RequestMethod.POST)
public class Account extends BaseController {

    @Autowired
    private AccountService accountService;

    /**
     * 注册的接口
     * @param username 用户名
     * @param password 密码
     * @param phone 手机号
     * @param email 邮箱
     * @param portrait 头像base64
     * @return
     */
    @ApiOperation(value = "注册", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String"),
            @ApiImplicitParam(name = "portrait", value = "头像", required = false, dataType = "String")/* 头像base64 */
    })
    @RequestMapping(value = "/register")
    public APIResponse register(@RequestParam(value = "username") String username, /* 用户名 */
                                @RequestParam(value = "password") String password, /* 密码 */
                                @RequestParam(value = "phone") String phone, /* 手机号 */
                                @RequestParam(value = "email") String email, /* 邮箱 */
                                @RequestParam(value = "portrait") String portrait /* 头像base64 */
    ) {

        logger.info("================= 注册接口 =================");

        if (!Common.validString(username) || !Common.validString(password)) {
            return this.fail("请输入账号、密码");
        }
        HashMap<String, Object> users = accountService.findUserByUsername(username);
        if (users != null) {
            return this.fail("用户已存在");
        } else {
            HashMap<String, Object> user = new HashMap<>();
            String user_id = Common.UUID(); //生成用户的id
            user.put("user_id", user_id);
            user.put("username", username);
            password = new SimpleHash("SHA-1", username, password).toString(); //给密码进行加密
            user.put("password", password);
            user.put("phone", phone);
            user.put("email", email);
            String imageFullName = FileTools.saveBase64Image(portrait, user_id); //保存图片
            user.put("portrait", imageFullName);
            user.put("create_time", DateTools.timesNow());
            accountService.add(user);
            return this.success("操作成功");
        }
    }

    /**
     * 登录接口
     * @param username 账号
     * @param password 密码
     * @return
     */
    @ApiOperation(value = "登录", notes = "账号密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/login")
    public APIResponse login(@RequestParam(value = "username") String username, /* 用户名 */
                             @RequestParam(value = "password") String password /* 密码 */
    ) {

        logger.info("================= 登录接口 =================");

        if (!Common.validString(username) || !Common.validString(password)) {
            return this.fail("请输入账号、密码");
        }

        // 查询用户
        String pwdCrypto = new SimpleHash("SHA-1", username, password).toString();    //密码加密
        HashMap<String, Object> user = accountService.findUser(username, pwdCrypto);
        if (user == null) {
            return this.fail("用户名或密码错误，登录失败");
        }

        // 添加token
        user.put("token", JwtTools.genToken(user));

        return this.success(user);
    }

}
