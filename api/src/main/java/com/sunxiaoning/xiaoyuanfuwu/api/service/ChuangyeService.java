package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.HashMap;
import java.util.List;

/**
 * 兼职/招聘逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface ChuangyeService {

    /**
     * 添加兼职/招聘
     *
     * @param chuangye
     * @return
     */
    Integer addChuangye(HashMap<String, Object> chuangye);

    /**
     * 查询所有的兼职/招聘信息
     *
     * @return
     */
    List selChuangye();

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
