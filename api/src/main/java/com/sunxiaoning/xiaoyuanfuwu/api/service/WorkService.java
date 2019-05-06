package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.HashMap;

/**
 * 兼职/招聘逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkService {

    /**
     * 添加兼职/招聘
     *
     * @param work
     * @return
     */
    Integer addWork(HashMap<String, Object> work);


}
