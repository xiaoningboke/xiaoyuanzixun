package com.sunxiaoning.xiaoyuanfuwu.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxiaoning.xiaoyuanfuwu.api.service.AccountService;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ExitUserMessageService;
import com.sunxiaoning.xiaoyuanfuwu.common.controller.BaseController;
import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import com.sunxiaoning.xiaoyuanfuwu.tools.Common;
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

@Api("账号管理")
@RestController
@RequestMapping(value = "/api/usermessage", method = RequestMethod.POST)
public class UserMessage extends BaseController {

    @Autowired
    private ExitUserMessageService exitUserMessageService;
    private AccountService accountService;
    /**
     * 修改用户的个人信息
     *
     * @param token    token
     * @param username 用户名
     * @param phone    手机号
     * @param email    邮箱
     * @return
     */
    @ApiOperation(value = "修改个人基本信息", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "token"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = false, dataType = "String")
    })
    @RequestMapping(value = "/exitusermessage")
    public APIResponse exitusermessage(@RequestParam(value = "token") String token, /* token*/
                                @RequestParam(value = "username") String username, /* 用户名 */
                                @RequestParam(value = "phone") String phone, /* 手机号 */
                                @RequestParam(value = "email") String email /* 邮箱 */
    ) {

        logger.info("================= 用户基本信息修改 =================");

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

            HashMap<String, Object> user = new HashMap<>();
            user.put("user_id", user_id);
            user.put("username", username);
            user.put("phone", phone);
            user.put("email", email);
            Integer f = exitUserMessageService.updateUserMassage(user);
            if (f > 0) {
                return this.success("操作成功");
            } else {
                return this.fail("没有进行修改");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return this.fail("操作失败");
        }
    }

    @ApiOperation(value = "修改个人密码", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "token"),
            @ApiImplicitParam(name = "password", value = "原密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newpassword", value = "新密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "/exitpassword")
    public APIResponse exitpassword(@RequestParam(value = "token") String token, /* token*/
                                @RequestParam(value = "password") String password, /* 原密码 */
                                @RequestParam(value = "newpassword") String newpassword /* 新密码 */
    ) {

        logger.info("================= 用户密码修改 =================");

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
            String username = tokenUser.getString("username");

            //判断输入是否正确
            if (!Common.validString(password) || !Common.validString(newpassword)) {
                return this.fail("请输入原密码以及新密码");
            }

            // 查询用户
            String pwdCrypto = new SimpleHash("SHA-1", username, password).toString();    //密码加密
            HashMap<String, Object> users = accountService.findUser(username, pwdCrypto);
            if (users == null) {
                return this.fail("原密码错误");
            }

            HashMap<String, Object> user = new HashMap<>();
            String newPassword = new SimpleHash("SHA-1", username, newpassword).toString();
            user.put("user_id", user_id);
            user.put("password", newPassword);
            Integer f = exitUserMessageService.updatePassworld(user);
            if (f > 0) {
                return this.success("操作成功");
            } else {
                return this.fail("没有进行修改");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return this.fail("操作失败");
        }
    }

}
