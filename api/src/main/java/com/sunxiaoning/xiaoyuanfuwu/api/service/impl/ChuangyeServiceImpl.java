package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.controller.Chuangye;
import com.sunxiaoning.xiaoyuanfuwu.api.dao.ChuangyeDao;
import com.sunxiaoning.xiaoyuanfuwu.api.dao.WorkDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.ChuangyeService;
import com.sunxiaoning.xiaoyuanfuwu.api.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class ChuangyeServiceImpl implements ChuangyeService {

    @Autowired
    private ChuangyeDao chuangyeDao;


    @Override
    public Integer addChuangye(HashMap<String, Object> chuangye) {
        return chuangyeDao.addChuangye(
                chuangye.get("chuangye_id").toString(),
                chuangye.get("chuangye_name").toString(),
                chuangye.get("chuangye_create_time").toString(),
                chuangye.get("chuangye_con").toString(),
                chuangye.get("chuangye_update_time").toString(),
                chuangye.get("chuangye_user").toString()
        );
    }

    @Override
    public List selChuangye() {
        return chuangyeDao.selWork();
    }

    @Override
    public HashMap findWork(String work_id) {
        return chuangyeDao.findWork(work_id);
    }

    @Override
    public Integer baoMing(HashMap userwork) {
        return chuangyeDao.baoMing(
                userwork.get("user_work_id").toString(),
                userwork.get("user_id").toString(),
                userwork.get("work_id").toString(),
                userwork.get("user_work_state").toString()
        );
    }
}
