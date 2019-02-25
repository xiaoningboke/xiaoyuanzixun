package com.sunxiaoning.xiaoyuanfuwu.common.controller;

import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BaseController {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回成功Response
     *
     * @param data
     * @return
     */
    public APIResponse success(Object data) {
        logger.info("请求结束 :success: =>", data.toString());
        APIResponse res = new APIResponse();
        res.success(data);
        return res;
    }


    /**
     * 分页列表
     *
     * @param data
     * @param pageIndex
     * @param pageSize
     * @param total
     * @return
     */
    public APIResponse successWithPage(Object data, int pageIndex, int pageSize, int total) {
        logger.info("请求结束 :success: =>", data.toString());
        APIResponse res = new APIResponse();

        HashMap<String, Object> pageResponse = new HashMap<>();
        pageResponse.put("data", data);
        pageResponse.put("current", pageIndex);
        pageResponse.put("hasMore", pageIndex * pageSize < total);
        pageResponse.put("total", total);

        res.success(pageResponse);
        return res;
    }

    /**
     * 返回错误Response
     *
     * @param errmsg
     * @return
     */
    public APIResponse fail(String errmsg) {
        logger.info("请求结束 :fail: =>", errmsg);
        APIResponse res = new APIResponse();
        res.fail(1, errmsg);
        return res;
    }

}
