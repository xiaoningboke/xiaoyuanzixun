package com.sunxiaoning.xiaoyuanfuwu.api.service.impl;

import com.sunxiaoning.xiaoyuanfuwu.api.dao.AccountDao;
import com.sunxiaoning.xiaoyuanfuwu.api.dao.WorkDao;
import com.sunxiaoning.xiaoyuanfuwu.api.service.AccountService;
import com.sunxiaoning.xiaoyuanfuwu.api.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkDao workDao;


    @Override
    public Integer addWork(HashMap<String, Object> work) {
        return workDao.addWork(
                work.get("work_id").toString(),
                work.get("user_id").toString(),
                work.get("work_name").toString(),
                work.get("work_number").toString(),
                work.get("work_money").toString(),
                work.get("work_unit").toString(),
                work.get("work_start_time").toString(),
                work.get("work_end_time").toString(),
                work.get("work_content").toString(),
                work.get("work_type").toString(),
                work.get("work_user").toString(),
                work.get("work_phone").toString(),
                work.get("work_pic").toString()
        );
    }
}
