package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.ZixunDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ZixunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class ZixunServiceImpl implements ZixunService {

    @Autowired
    private ZixunDao zixunDao;


    @Override
    public Integer addZixun(HashMap<String, Object> zixun) {
        return zixunDao.addZixun(
                zixun.get("news_id").toString(),
                zixun.get("news_user_id").toString(),
                zixun.get("news_title").toString(),
                zixun.get("news_user").toString(),
                zixun.get("news_time").toString(),
                zixun.get("news_exit_time").toString(),
                zixun.get("news_classify").toString(),
                zixun.get("news_con").toString(),
                zixun.get("new_pic").toString()
        );
    }


    @Override
    public List selZixun(String news_classify) {
        return zixunDao.selZixun(news_classify);
    }

    @Override
    public HashMap findNews(String news_id) {
        return zixunDao.findNews(news_id);
    }

    @Override
    public Integer addShiwu(HashMap shiwu) {
        return zixunDao.addShiwu(
                shiwu.get("lost_id").toString(),
                shiwu.get("lost_user").toString(),
                shiwu.get("lost_user_id").toString(),
                shiwu.get("lost_time").toString(),
                shiwu.get("lost_con").toString()
        );
    }

    @Override
    public List selShiwu() {
        return zixunDao.selShiwu();
    }

    @Override
    public HashMap findShiwu(String lost_id) {
        return zixunDao.findShiwu(lost_id);
    }

    @Override
    public Integer lingqu(HashMap lingqu) {
        return zixunDao.lingqu(
                lingqu.get("lost_id").toString(),
                lingqu.get("lost_owner_id").toString(),
                lingqu.get("lost_owner").toString(),
                lingqu.get("lost_receive_time").toString()
        );
    }
}
