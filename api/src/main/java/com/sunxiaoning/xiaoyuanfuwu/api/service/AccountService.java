package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.HashMap;

/**
 * 账号业务逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface AccountService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    HashMap<String, Object> findUserByUsername(String username);

    /**
     * 添加用户
     *
     * @param obj 用户信息
     * @return
     */
    Integer add(HashMap<String, Object> obj);

    /**
     * 根据用户名密码查询
     *
     * @param username 用户名
     * @param password 密码
     */
    HashMap<String, Object> findUser(String username, String password);

}
