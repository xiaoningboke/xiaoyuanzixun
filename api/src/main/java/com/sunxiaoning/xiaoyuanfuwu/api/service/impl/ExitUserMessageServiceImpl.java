package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.ExitUserMassageDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ExitUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;


@Service
public class ExitUserMessageServiceImpl implements ExitUserMessageService {

    @Autowired
    private ExitUserMassageDao exitUserMassageDao;


    @Override
    public Integer updateUserMassage(HashMap<String, Object> obj) {
        return exitUserMassageDao.updateUserMassage(
                obj.get("user_id").toString(),
                obj.get("username").toString(),
                obj.get("phone").toString(),
                obj.get("email").toString()
        );
    }

    @Override
    public Integer updatePassworld(HashMap<String, Object> obj) {
        return exitUserMassageDao.updatePassworld(
                obj.get("user_id").toString(),
                obj.get("newpassword").toString()
        );
    }

    @Override
    public Integer addAddress(HashMap<String, Object> address) {
        return exitUserMassageDao.addAddress(
                address.get("address_id").toString(),
                address.get("user_id").toString(),
                address.get("contacts").toString(),
                address.get("tell").toString(),
                address.get("address").toString(),
                address.get("street").toString(),
                address.get("remarks").toString(),
                address.get("state").toString()
        );
    }

    @Override
    public Integer updateState(String address_id, int state) {
        return exitUserMassageDao.updateState(address_id, state);
    }

    @Override
    public Integer updateStatus(String address_id, int state) {
        return exitUserMassageDao.updateStatus(address_id, state);
    }

    @Override
    public Integer updateAddress(HashMap<String, Object> address) {
        return exitUserMassageDao.updateAddress(
                address.get("address_id").toString(),
                address.get("contacts").toString(),
                address.get("tell").toString(),
                address.get("address").toString(),
                address.get("street").toString(),
                address.get("remarks").toString(),
                address.get("state").toString()
        );
    }

    @Override
    public HashMap<String, Object> findAddress(String address_id, String user_id) {
        return exitUserMassageDao.findAddress(
                address_id,
                user_id
        );
    }

    @Override
    public Integer deleteAddress(String address_id) {
        return exitUserMassageDao.deleteAddress(address_id);
    }

    @Override
    public ArrayList selAddress(String user_id) {
        return exitUserMassageDao.selAddress(user_id);
    }

}
