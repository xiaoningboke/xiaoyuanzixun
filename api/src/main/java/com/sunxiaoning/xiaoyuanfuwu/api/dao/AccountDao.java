package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;


import java.util.HashMap;

/**
 * 账号 DAO 接口类
 */
public interface AccountDao {


    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return
     */
    HashMap<String, Object> findUserByUsername(@Param("username") String username);

    /**
     * 添加用户
     *
     * @param obj 用户信息
     * @return
     */
    Integer add(HashMap<String, Object> obj);

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param password 密码
     */
    HashMap<String, Object> findUserByAccount(@Param("username") String username,
                                              @Param("password") String password);

}
