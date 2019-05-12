package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.HashMap;
import java.util.List;

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

    /**
     * 查询所有的兼职/招聘信息
     *
     * @return
     */
    List selWork(Integer work_type);

    /**
     * 查询兼职的详情
     *
     * @param work_id 工作id
     * @return
     */
    HashMap findWork(String work_id);

    /**
     * 报名参加工作
     *
     * @param userwork 用户工作信息
     * @return
     */
    Integer baoMing(HashMap userwork);
}
