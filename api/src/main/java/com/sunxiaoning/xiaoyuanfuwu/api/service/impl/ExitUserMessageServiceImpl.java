package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.ExitUserMassageDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ExitUserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Integer updatePassworld(HashMap<String,Object> obj){
        return exitUserMassageDao.updatePassworld(
                obj.get("user_id").toString(),
                obj.get("newpassword").toString()
        );
    }


}
