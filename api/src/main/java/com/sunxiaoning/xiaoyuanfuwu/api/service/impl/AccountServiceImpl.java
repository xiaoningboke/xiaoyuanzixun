package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.AccountDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;


    @Override
    public HashMap<String, Object> findUserByUsername(String username) {
        return accountDao.findUserByUsername(username);
    }

    @Override
    public Integer add(HashMap<String, Object> obj) {
        return accountDao.add(obj);
    }

    @Override
    public HashMap<String, Object> findUser(String username, String password) {
        return accountDao.findUserByAccount(username, password);
    }

}
