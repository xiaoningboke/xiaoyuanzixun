package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.ArrayList;
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

    /**
     * 添加用户的地址
     *
     * @return
     */
    Integer addAddress(HashMap<String, Object> address);

    /**
     * 跟新状态
     *
     * @param address_id
     * @param state
     * @return
     */
    Integer updateState(String address_id, int state);

    /**
     * 更新状态
     *
     * @param address_id
     * @param state
     * @return
     */
    Integer updateStatus(String address_id, int state);

    /**
     * 修改地址信息
     *
     * @param address
     * @return
     */
    Integer updateAddress(HashMap<String, Object> address);

    /**
     * 判断地址是不是属于该用户
     *
     * @param address_id 地址id
     * @param user_id    用户id
     * @return
     */
    HashMap<String, Object> findAddress(String address_id, String user_id);

    /**
     * 删除用户的地址
     *
     * @param address_id
     * @return
     */
    Integer deleteAddress(String address_id);

    /**
     * 查询该用户的所有地址
     *
     * @param user_id 用户id
     * @return
     */
    ArrayList selAddress(String user_id);
}
