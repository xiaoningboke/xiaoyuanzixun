package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * j兼职招聘 DAO 接口类
 */
public interface ChuangyeDao {




    Integer addChuangye(
            @Param("chuangye_id") String chuangye_id,
            @Param("chuangye_name") String chuangye_name,
            @Param("chuangye_create_time") String chuangye_create_time,
            @Param("chuangye_con") String chuangye_con,
            @Param("chuangye_update_time") String chuangye_update_time,
            @Param("chuangye_user") String chuangye_user
    );

    /**
     * 查询所有的兼职/招聘
     ** @return
     */
    List selWork();

    /**
     * 兼职/招聘的信息
     *
     * @param work_id 工作id
     * @return
     */
    HashMap findWork(@Param("work_id") String work_id);

    /**
     * 报名兼职
     *
     * @param user_work_id
     * @param user_id
     * @param work_id
     * @param user_work_state
     * @return
     */
    Integer baoMing(
            @Param("user_work_id") String user_work_id,
            @Param("user_id") String user_id,
            @Param("work_id") String work_id,
            @Param("user_work_state") String user_work_state
    );


}
