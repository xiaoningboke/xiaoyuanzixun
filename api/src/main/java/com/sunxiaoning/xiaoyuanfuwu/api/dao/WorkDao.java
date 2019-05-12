package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * j兼职招聘 DAO 接口类
 */
public interface WorkDao {


    /**
     * 添加兼职招聘信息
     *
     * @param work_id
     * @param user_id
     * @param work_name
     * @param work_number
     * @param work_money
     * @param work_unit
     * @param work_start_time
     * @param work_end_time
     * @param work_content
     * @param work_type
     * @param work_user
     * @param work_phone
     * @param work_pic
     * @return
     */
    Integer addWork(
            @Param("work_id") String work_id,
            @Param("user_id") String user_id,
            @Param("work_name") String work_name,
            @Param("work_number") String work_number,
            @Param("work_money") String work_money,
            @Param("work_unit") String work_unit,
            @Param("work_start_time") String work_start_time,
            @Param("work_end_time") String work_end_time,
            @Param("work_content") String work_content,
            @Param("work_type") String work_type,
            @Param("work_user") String work_user,
            @Param("work_phone") String work_phone,
            @Param("work_pic") String work_pic
    );

    /**
     * 查询所有的兼职/招聘
     *
     * @param work_type 工作类型
     * @return
     */
    List selWork(@Param("work_type") Integer work_type);

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
