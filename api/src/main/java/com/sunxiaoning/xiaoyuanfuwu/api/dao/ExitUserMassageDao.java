package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
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

    /**
     * 用户添加地址
     *
     * @param address_id id
     * @param user_id    用户id
     * @param contacts   联系人
     * @param tell       联系方式
     * @param address    地址
     * @param street     街道
     * @param remarks    备注
     * @param state      状态（0默认）
     * @return
     */
    Integer addAddress(
            @Param("address_id") String address_id,
            @Param("user_id") String user_id,
            @Param("contacts") String contacts,
            @Param("tell") String tell,
            @Param("address") String address,
            @Param("street") String street,
            @Param("remarks") String remarks,
            @Param("state") String state
    );

    /**
     * 修改默认地址
     *
     * @param address_id
     * @param state
     * @return
     */
    Integer updateState(
            @Param("address_id") String address_id,
            @Param("state") Integer state
    );

    /**
     * 修改默认地址
     *
     * @param address_id
     * @param state
     * @return
     */
    Integer updateStatus(
            @Param("address_id") String address_id,
            @Param("state") Integer state
    );

    /**
     * 修改地址信息
     *
     * @param address_id
     * @param contacts
     * @param tell
     * @param address
     * @param street
     * @param remarks
     * @param state
     * @return
     */
    Integer updateAddress(
            @Param("address_id") String address_id,
            @Param("contacts") String contacts,
            @Param("tell") String tell,
            @Param("address") String address,
            @Param("street") String street,
            @Param("remarks") String remarks,
            @Param("state") String state
    );

    /**
     * 判断地址属不属于该用户
     *
     * @param address_id 地址id
     * @param user_id    用户id
     * @return
     */
    HashMap<String, Object> findAddress(
            @Param("address_id") String address_id,
            @Param("user_id") String user_id
    );

    /**
     * 删除某个地址
     *
     * @param address_id 地址的id
     * @return
     */
    Integer deleteAddress(@Param("address_id") String address_id);

    /**
     * 查询该用户的所有地址
     *
     * @param user_id 用户id
     * @return
     */
    ArrayList selAddress(@Param("user_id") String user_id);
}
