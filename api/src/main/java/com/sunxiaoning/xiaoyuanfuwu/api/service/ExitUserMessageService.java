package com.sunxiaoning.xiaoyuanfuwu.api.service;

import io.swagger.models.auth.In;

import java.util.HashMap;

/**
 * 账号业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface ExitUserMessageService {

    /**
     * 修改用户个人资料
     *
     * @param obj 用户信息
     * @return
     */
    Integer updateUserMassage(HashMap<String, Object> obj);

    /**
     * 修改用户的密码
     *
     * @param obj 用户信息
     * @return
     */
    Integer updatePassworld(HashMap<String, Object> obj);

}
