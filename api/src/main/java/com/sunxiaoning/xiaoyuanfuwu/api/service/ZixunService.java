package com.sunxiaoning.xiaoyuanfuwu.api.service;

import java.util.HashMap;
import java.util.List;

/**
 * 新闻资讯逻辑接口类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface ZixunService {

    /**
     * 添加新闻资讯
     *
     * @param work
     * @return
     */
    Integer addZixun(HashMap<String, Object> work);

    /**
     * 查询所有的兼职/招聘信息
     *
     * @return
     */
    List selZixun(String news_classify);

    /**
     * 查询新闻资讯详情
     *
     * @param news_id 新闻id
     * @return
     */
    HashMap findNews(String news_id);

    /**
     * 发布失物招领
     *
     * @param shiwu
     * @return
     */
    Integer addShiwu(HashMap shiwu);

    /**
     * 失物招领列表
     *
     * @return
     */
    List selShiwu();

    HashMap findShiwu(String lost_id);

    /**
     * 领取失物招领
     *
     * @param lingqu
     * @return
     */
    Integer lingqu(HashMap lingqu);
}
