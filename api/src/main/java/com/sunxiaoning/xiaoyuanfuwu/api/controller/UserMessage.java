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

import java.util.ArrayList;
import java.util.HashMap;

@Api("账号管理")
@RestController
@RequestMapping(value = "/api/usermessage", method = RequestMethod.POST)
public class UserMessage extends BaseController {

    public static final int FEIMOREN = 1;
    public static final int MOREN = 0;

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

    /**
     * 添加地址的接口
     *
     * @param token
     * @param contacts
     * @param tell
     * @param address
     * @param street
     * @param remarks
     * @param state
     * @return
     */
    @ApiOperation(value = "添加自己地址", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contacts", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "tell", value = "联系电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "street", value = "街道", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "备注", required = false, dataType = "String")
    })
    @RequestMapping(value = "/addaddress")
    public APIResponse addAddress(@RequestParam(value = "token") String token, /* token */
                                  @RequestParam(value = "contacts") String contacts, /* 联系人 */
                                  @RequestParam(value = "tell") String tell, /* 联系电话 */
                                  @RequestParam(value = "address") String address, /* 地址 */
                                  @RequestParam(value = "street") String street, /* 街道 */
                                  @RequestParam(value = "remarks") String remarks, /* 备注 */
                                  @RequestParam(value = "state", defaultValue = "1") String state /* 备注 */
    ) {

        logger.info("================= 添加地址接口 =================");

        if (!Common.validString(token)) {
            return this.fail("请您先登录");
        }
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

        HashMap<String, Object> dizhi = new HashMap<>();
        String address_id = Common.UUID(); //
        dizhi.put("address_id", address_id);
        dizhi.put("user_id", user_id);
        dizhi.put("contacts", contacts);
        dizhi.put("tell", tell);
        dizhi.put("address", address);
        dizhi.put("street", street);
        dizhi.put("remarks", remarks);
        if (Integer.valueOf(state) == 0) {
            exitUserMessageService.updateState(address_id, FEIMOREN);
        }
        dizhi.put("state", state);
        Integer f = exitUserMessageService.addAddress(dizhi);
        if (f > 0) {
            return this.success("添加成功");
        } else {
            return this.fail("添加失败");
        }
    }

    /**
     * 修改默认地址
     *
     * @param token
     * @param address_id
     * @return
     */
    @ApiOperation(value = "把地址设置为默认地址", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address_id", value = "地址id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/updateaddressstate")
    public APIResponse updateAddressState(@RequestParam(value = "token") String token, /* token */
                                          @RequestParam(value = "address_id") String address_id /* 地址id */
    ) {
        logger.info("================= 把地址设置为默认地址 =================");

        if (!Common.validString(token)) {
            return this.fail("请您先登录");
        }
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

        HashMap<String, Object> dz = exitUserMessageService.findAddress(address_id, user_id);
        if (dz == null) {
            return this.fail("您没有权限");
        }

        Integer f = exitUserMessageService.updateStatus(address_id, MOREN);
        exitUserMessageService.updateState(address_id, FEIMOREN);

        if (f > 0) {
            return this.success("修改成功");
        } else {
            return this.fail("修改成功");
        }
    }

    /**
     * 修改地址信息
     *
     * @param token      token
     * @param address_id 地址id
     * @param contacts   联系人
     * @param tell       联系电话
     * @param address    地址
     * @param street     街道
     * @param remarks    备注
     * @param state      状态（0 默认）
     * @return
     */
    @ApiOperation(value = "修改地址", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address_id", value = "地址id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contacts", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "tell", value = "联系电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "street", value = "街道", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remarks", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(name = "state", value = "备注", required = false, dataType = "String")
    })
    @RequestMapping(value = "/updateaddress")
    public APIResponse updateAddress(@RequestParam(value = "token") String token, /* token */
                                     @RequestParam(value = "address_id") String address_id, /* 地址id */
                                     @RequestParam(value = "contacts") String contacts, /* 联系人 */
                                     @RequestParam(value = "tell") String tell, /* 联系电话 */
                                     @RequestParam(value = "address") String address, /* 地址 */
                                     @RequestParam(value = "street") String street, /* 街道 */
                                     @RequestParam(value = "remarks") String remarks, /* 备注 */
                                     @RequestParam(value = "state", defaultValue = "1") String state /* 备注 */
    ) {

        logger.info("================= 修改地址接口 =================");

        if (!Common.validString(token)) {
            return this.fail("请您先登录");
        }
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

        HashMap<String, Object> dz = exitUserMessageService.findAddress(address_id, user_id);
        if (dz == null) {
            return this.fail("您没有权限");
        }

        HashMap<String, Object> dizhi = new HashMap<>();
        dizhi.put("address_id", address_id);
        dizhi.put("contacts", contacts);
        dizhi.put("tell", tell);
        dizhi.put("address", address);
        dizhi.put("street", street);
        dizhi.put("remarks", remarks);
        if (Integer.valueOf(state) == 0) {
            exitUserMessageService.updateState(address_id, FEIMOREN);
        }
        dizhi.put("state", state);
        Integer f = exitUserMessageService.updateAddress(dizhi);
        if (f > 0) {
            return this.success("修改成功");
        } else {
            return this.fail("修改失败");
        }
    }


    /**
     * 查询该用户的所有地址
     *
     * @param token token
     * @return
     */
    @ApiOperation(value = "查询该用户的所有地址", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String")
    })
    @RequestMapping(value = "/seladdress")
    public APIResponse selAddress(@RequestParam(value = "token") String token /* token */
    ) {
        logger.info("================= 查询该用户的所有地址 =================");

        if (!Common.validString(token)) {
            return this.fail("请您先登录");
        }
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

        ArrayList address = exitUserMessageService.selAddress(user_id);

        return this.success(address);
    }

    /**
     * 删除某个地址（逻辑删除）
     *
     * @param token      token
     * @param address_id 地址id
     * @return
     */
    @ApiOperation(value = "删除某个地址", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address_id", value = "地址id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/deleteaddress")
    public APIResponse deleteAddress(@RequestParam(value = "token") String token, /* token */
                                     @RequestParam(value = "address_id") String address_id /* 地址id */
    ) {
        logger.info("================= 删除（逻辑删除）某个地址 =================");

        if (!Common.validString(token)) {
            return this.fail("请您先登录");
        }
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

        HashMap<String, Object> dz = exitUserMessageService.findAddress(address_id, user_id);
        if (dz == null) {
            return this.fail("您没有权限");
        }

        Integer f = exitUserMessageService.deleteAddress(address_id);

        if (f > 0) {
            return this.success("删除成功");
        } else {
            return this.fail("删除失败");
        }
    }


}
