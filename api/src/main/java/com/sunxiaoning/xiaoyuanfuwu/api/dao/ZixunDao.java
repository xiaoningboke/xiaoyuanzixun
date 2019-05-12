package com.sunxiaoning.xiaoyuanfuwu.api.dao;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * j兼职招聘 DAO 接口类
 */
public interface ZixunDao {


    /**
     * 添加资讯
     *
     * @param news_id
     * @param news_user_id
     * @param news_user
     * @param news_time
     * @param news_exit_time
     * @param news_classify
     * @param news_con
     * @param new_pic
     * @return
     */
    Integer addZixun(
            @Param("news_id") String news_id,
            @Param("news_user_id") String news_user_id,
            @Param("news_title") String news_title,
            @Param("news_user") String news_user,
            @Param("news_time") String news_time,
            @Param("news_exit_time") String news_exit_time,
            @Param("news_classify") String news_classify,
            @Param("news_con") String news_con,
            @Param("new_pic") String new_pic

    );

    /**
     * 查询新闻资讯
     *
     * @param news_classify
     * @return
     */
    List selZixun(@Param("news_classify") String news_classify);

    /**
     * 兼职/招聘的信息
     *
     * @param news_id 工作id
     * @return
     */
    HashMap findNews(@Param("news_id") String news_id);

    /**
     * 发布失物招领
     *
     * @param lost_id
     * @param lost_user
     * @param lost_user_id
     * @param lost_time
     * @param lost_con
     * @return
     */
    Integer addShiwu(
            @Param("lost_id") String lost_id,
            @Param("lost_user") String lost_user,
            @Param("lost_user_id") String lost_user_id,
            @Param("lost_time") String lost_time,
            @Param("lost_con") String lost_con
    );

    /**
     * 失物招领列表
     *
     * @return
     */
    List selShiwu();

    /**
     * 失物招领的详情
     *
     * @param lost_id
     * @return
     */
    HashMap findShiwu(@Param("lost_id") String lost_id);

    Integer lingqu(
            @Param("lost_id") String lost_id,
            @Param("lost_owner_id") String lost_owner_id,
            @Param("lost_owner") String lost_owner,
            @Param("lost_receive_time") String lost_receive_time
    );
}
