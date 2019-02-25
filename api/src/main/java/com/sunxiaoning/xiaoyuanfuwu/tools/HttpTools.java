package com.sunxiaoning.xiaoyuanfuwu.tools;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class HttpTools {

    /**
     * 将某个对象转换成json格式并发送到客户端
     * @param response
     * @param obj
     * @throws Exception
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {

        // 设置JSON方式
        response.setContentType("application/json; charset=utf-8");

        // 输出信息
        PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSON(obj));
        writer.close();
        response.flushBuffer();
    }
}
