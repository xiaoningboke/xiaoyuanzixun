package com.sunxiaoning.xiaoyuanfuwu.common.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APIResponse {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final int ERROR_PARAM_INVALID = 40001;
    public static final int ERROR_AUTH_FAIL = 44001; // 认证信息失败


    // 信息代码
    private int errno;
    // 返回信息
    private String errmsg;
    // 返回内容
    private Object data;


    /**
     * 返回错误信息
     * @param errno
     * @param errmsg
     */
    public void fail(int errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg != null ? errmsg : "";
        this.data = "";

        // 整体错误
        if (errno == ERROR_PARAM_INVALID) {
            this.errmsg = "参数不合法";
        } else if (errno == ERROR_AUTH_FAIL) {
            this.errmsg = "信息认证失败";
        }
    }


    /**
     * 分页数据查询
     *
     * @param list     业务数据集合
     * @param count    总条数
     * @param current  当前页码
     * @param pageSize 每页数量
     */
    public void setPageData(List list, Integer count, Integer current, Integer pageSize) {

        HashMap<String, Object> data = new HashMap<>();
        data.put("totalAmount", count); // 符合条件的总条数
        data.put("current", current);  // 当前页码
        data.put("amount", (count + pageSize - 1) / pageSize); // 总页数(向上取整)

        // 容错数组类型
        if (list != null) {
            data.put("data", list); // 业务数据
        } else {
            data.put("data", new ArrayList<>()); // 空业务数据
        }

        this.data = data;
    }

    /**
     * 返回成功信息
     * @param data
     */
    public void success(Object data) {
        this.errno = 0;
        this.errmsg = "";
        this.data = data;
    }


    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
