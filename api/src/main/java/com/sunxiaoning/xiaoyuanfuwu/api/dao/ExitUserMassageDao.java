package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 * 账号 DAO 接口类
 */
public interface ExitUserMassageDao {


    /**
     * 修改个人信息
     *
     * @param user_id  用户的id
     * @param username 用户名
     * @param phone    用户手机
     * @param email    用户邮箱
     * @return
     */
    Integer updateUserMassage(@Param("user_id") String user_id,
                              @Param("username") String username,
                              @Param("phone") String phone,
                              @Param("email") String email
    );

    /**
     * 修改用户密码
     *
     * @param user_id  用户的id
     * @param password 用户的新密码
     * @return
     */
    Integer updatePassworld(@Param("user_id") String user_id,
                            @Param("newpassword") String password
    );

}
